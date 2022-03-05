public class Main {

    public static void main(String... args) {

        GameController gameController = new GameController();
        GameWindow gameWindow = new GameWindow(500,500, gameController);

        // Linking
        gameWindow.getGamePanel().setGameImage(gameController.getOutputImage());
        gameController.getGameOfLife().addTickObserver(gameWindow.getGamePanel());
        gameController.addGameboardObserver(gameWindow.getGamePanel());
        gameWindow.getGamePanel().addLifeObserver(gameController);

        // Params
        gameController.resetGameBoard(Shapes.GOSPER_GLIDER_GUN);
        gameController.setGameSpeed(10);

        gameWindow.setVisible(true);


    }
}
