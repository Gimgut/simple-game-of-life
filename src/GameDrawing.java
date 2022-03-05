import java.awt.*;
import java.awt.image.BufferedImage;

public class GameDrawing {

    private int viewWidth, viewHeight;
    private BufferedImage gameImage;
    private Graphics graphicsGame;
    private int scale;
    private GameOfLife gameOfLife;
    private Color backgroundColor = Color.BLACK;
    private Color cellColor = Color.GRAY;
    private int squareEdge = 1;

    public GameDrawing(int viewHeight, int viewWidth, int scale, GameOfLife gameOfLife) {
        this.viewHeight = viewHeight;
        this.viewWidth = viewWidth;
        this.gameImage = new BufferedImage(viewWidth, viewHeight, BufferedImage.TYPE_INT_RGB);
        this.graphicsGame = gameImage.getGraphics();
        this.scale = scale;
        this.gameOfLife = gameOfLife;

        prepareDrawing();
    }

    public synchronized void prepareDrawing() {
        boolean[][] grid = gameOfLife.getActualGrid().getGrid();

        graphicsGame.setColor(backgroundColor);
        graphicsGame.fillRect(0,0, viewWidth, viewHeight);

        int hMax = gameOfLife.getGridHeight();
        int wMax = gameOfLife.getGridWidth();

        graphicsGame.setColor(cellColor);
        for (int h = 0; h < hMax; h++) {
            for (int w = 0; w < wMax; w++) {
                if (grid[h][w]) {
                    graphicsGame.fillRect(w * scale, h * scale, squareEdge * scale, squareEdge * scale);
                }
            }
        }
    }

    public synchronized void drawOne(int widthPos, int heightPos, boolean isAlive) {
        graphicsGame.setColor(isAlive ? cellColor : backgroundColor);
        graphicsGame.fillRect(widthPos * scale, heightPos * scale, squareEdge * scale, squareEdge * scale);
    }


    public BufferedImage getGameImage() {
        return gameImage;
    }

    public int getScale() {
        return scale;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }
}
