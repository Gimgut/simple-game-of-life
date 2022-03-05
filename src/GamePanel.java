import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements TickObserver, LifeEmitter, GameboardObserver {

    private BufferedImage gameImage;
    private List<LifeObserver> lifeObservers = new ArrayList();

    public GamePanel() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                notifyAllLifeObservers(e.getX(), e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notifyAllLifeObservers(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameImage !=null) {
            g.drawImage(gameImage, 0, 0, null);
        }
    }

    @Override
    public void onTickEvent(Object source) {
        updateUI();
    }

    public BufferedImage getGameImage() {
        return gameImage;
    }

    public void setGameImage(BufferedImage gameImage) {
        this.gameImage = gameImage;
    }

    @Override
    public void addLifeObserver(LifeObserver lifeObserver) {
        lifeObservers.add(lifeObserver);
    }

    @Override
    public void removeLifeObserver(LifeObserver lifeObserver) {
        lifeObservers.remove(lifeObserver);
    }

    @Override
    public void notifyAllLifeObservers(int widthPos, int heightPos) {
        lifeObservers.forEach( observer -> observer.onLifeEvent(widthPos, heightPos) );
        updateUI();
    }

    @Override
    public void refreshBoardDrawing() {
        updateUI();
    }
}
