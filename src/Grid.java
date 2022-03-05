public class Grid {

    private int height;
    private int width;
    private boolean[][] grid;

    public Grid(int height, int width) {
        this.width = width;
        this.height = height;
        grid = new boolean[height][width];
    }

    public void reset() {
        grid = new boolean[height][width];
    }

    public void setAlive(int heightPos, int widthPos, boolean alive) {
        grid[heightPos][widthPos] = alive;
    }

    public boolean[][] getGrid() {
        return grid;
    }
}
