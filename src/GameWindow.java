import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private GamePanel gamePanel;
    private GameController gameController;
    private GameMenu gameMenu;

    public GameWindow(int gameBoardWidth, int gameBoardHeight, GameController gameController) {
        this.setTitle("Game Of Life");
        this.setLocation(100,100);

        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(gameBoardWidth, gameBoardHeight));
        this.add(gamePanel, BorderLayout.CENTER);

        gameMenu = new GameMenu();
        gameMenu.setGameController(gameController);
        this.add(gameMenu.$$$getRootComponent$$$(), BorderLayout.WEST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.pack();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
