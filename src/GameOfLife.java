import java.util.ArrayList;
import java.util.List;

public class GameOfLife implements TickObservable {

    private long ticks = 0l;
    private boolean isOdd = false;
    private Grid gridEven;
    private Grid gridOdd;
    private int gridHeight;
    private int gridWidth;

    private List<TickObserver> tickObservers = new ArrayList<>();

    public GameOfLife(int gridHeight, int gridWidth, List<Point> aliveCells) {
        init(gridHeight, gridWidth, aliveCells);
    }

    public synchronized void init(int gridHeight, int gridWidth, List<Point> aliveCells) {
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        this.gridEven = new Grid(gridHeight, gridWidth);
        this.gridOdd = new Grid(gridHeight, gridWidth);

        populate(aliveCells);
    }

    /**
     * Does not clear the board, only adds alive cells from the list
     * @param aliveCells
     */
    public synchronized void populate(List<Point> aliveCells) {
        aliveCells.forEach( point -> gridEven.getGrid()[point.getHeightPos()][point.getWidthPos()] = true);
        aliveCells.forEach( point -> gridOdd.getGrid()[point.getHeightPos()][point.getWidthPos()] = true);
    }

    public synchronized void populate(int widthPos, int heightPos) {
        gridEven.getGrid()[heightPos][widthPos] = true;
        gridOdd.getGrid()[heightPos][widthPos] = true;
    }

    /**
     * Cleans the board and populates it
     * @param aliveCells
     */
    public synchronized void reset(List<Point> aliveCells) {
        gridEven.reset();
        gridOdd.reset();
        populate(aliveCells);
    }

    public synchronized Grid getActualGrid() {
        return isOdd ? gridOdd : gridEven;
    }

    public synchronized void doTick() {
        Grid gridSource = isOdd ? gridOdd : gridEven;
        Grid gridDestination = isOdd ? gridEven : gridOdd;

        int gridHeightBorder = gridHeight - 1;
        int gridWidthBorder = gridWidth - 1;
        for (int h = 1; h < gridHeightBorder; h++) {
            for (int w = 1; w < gridWidthBorder; w++) {

                int liveNeighbours = countLiveNeighbours(gridSource, h, w);

                if (gridSource.getGrid()[h][w] && liveNeighbours > 1 && liveNeighbours < 4) {
                    gridDestination.getGrid()[h][w] = true;
                } else if (!gridSource.getGrid()[h][w] && liveNeighbours == 3) {
                    gridDestination.getGrid()[h][w] = true;
                } else {
                    gridDestination.getGrid()[h][w] = false;
                }

            }
        }

        ticks++;
        isOdd=!isOdd;

        this.notifyAllTickObservers();
    }

    private int countLiveNeighbours(Grid grid, int heightPos, int widthPos) {
        int alive = 0;

        alive += grid.getGrid()[heightPos+1][widthPos] ? 1 : 0;
        alive += grid.getGrid()[heightPos-1][widthPos] ? 1 : 0;

        alive += grid.getGrid()[heightPos][widthPos+1] ? 1 : 0;
        alive += grid.getGrid()[heightPos][widthPos-1] ? 1 : 0;

        alive += grid.getGrid()[heightPos+1][widthPos+1] ? 1 : 0;
        alive += grid.getGrid()[heightPos-1][widthPos-1] ? 1 : 0;

        alive += grid.getGrid()[heightPos+1][widthPos-1] ? 1 : 0;
        alive += grid.getGrid()[heightPos-1][widthPos+1] ? 1 : 0;

        return alive;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public long getTicks() {
        return ticks;
    }

    @Override
    public synchronized void addTickObserver(TickObserver tickObserver) {
        tickObservers.add(tickObserver);
    }

    @Override
    public synchronized void removeTickObserver(TickObserver tickObserver) {
        tickObservers.remove(tickObserver);
    }

    @Override
    public void notifyAllTickObservers() {
        this.tickObservers.forEach(observer -> observer.onTickEvent(this));
    }
}
