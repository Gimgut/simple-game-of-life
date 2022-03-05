import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameController implements TickObserver, LifeObserver, GameboardObservable {

    private GameOfLife gameOfLife;
    private GameDrawing gameDrawing;
    private GameThread gameThread;
    private List<GameboardObserver> gameboardObservers = new ArrayList<>();

    public GameController() {
        initGame();
    }

    private void initGame() {
        initGame(100, 100,
                500, 500,
                5, 100,
                Shapes.PERIOD_THREE_PULSAR);
    }

    public void initGame(int gridHeight, int gridWidth,
                         int viewHeight, int viewWidth,
                         int viewScale, int gameTickInterval,
                         List<Point> aliveCells) {

        gameOfLife = new GameOfLife(gridHeight,gridWidth, aliveCells);
        gameDrawing = new GameDrawing(viewHeight, viewWidth,viewScale,gameOfLife);
        gameThread = new GameThread(gameOfLife, gameTickInterval);

        gameOfLife.addTickObserver(this);
    }

    public void resetGameBoard(List<Point> aliveCells) {
        gameOfLife.reset(aliveCells);
        gameDrawing.prepareDrawing();
        notifyAllGameboardObservers();
    }

    public void setGameSpeed(int ticksPerSecond) {
        gameThread.setTickInterval(1000/ticksPerSecond);
    }

    public BufferedImage getOutputImage() {
        return this.gameDrawing.getGameImage();
    }

    public void startGame() {
        if (gameThread.isRunning()) {
            return;
        }

        gameThread.start();
    }

    public void stopGame() {
        if (!gameThread.isRunning()) {
            return;
        }

        gameThread.stop();
    }

    public GameOfLife getGameOfLife() {
        return gameOfLife;
    }

    public GameDrawing getGameDrawing() {
        return gameDrawing;
    }

    public GameThread getGameThread() {
        return gameThread;
    }

    @Override
    public void onTickEvent(Object source) {
        // TODO: decouple preparation from ticks to increase performance in high freq scenarios
        // by introducing a counter per sec of "prepareDrawing" and reducing the calls number
        if (gameDrawing != null) {
            gameDrawing.prepareDrawing();
        }
    }

    @Override
    public void onLifeEvent(int widthPos, int heightPos) {
        if (
                widthPos < 0
                || widthPos >= gameDrawing.getViewWidth()
                || heightPos < 0
                || heightPos >= gameDrawing.getViewHeight()) {
            return;
        }
        widthPos /= gameDrawing.getScale();
        heightPos /= gameDrawing.getScale();
        gameOfLife.populate(widthPos, heightPos);
        gameDrawing.drawOne(widthPos, heightPos, true);
    }

    @Override
    public void addGameboardObserver(GameboardObserver gameboardObserver) {
        gameboardObservers.add(gameboardObserver);
    }

    @Override
    public void removeGameboardObserver(GameboardObserver gameboardObserver) {
        gameboardObservers.remove(gameboardObserver);
    }

    @Override
    public void notifyAllGameboardObservers() {
        gameboardObservers.forEach( observer -> observer.refreshBoardDrawing() );
    }
}
