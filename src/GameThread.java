import java.util.ArrayList;
import java.util.List;

public class GameThread implements Runnable, StopObservable {

    private boolean isRunning;
    private GameOfLife gameOfLife;
    private long tickInterval = 1000;
    private Thread thread;
    private List<StopObserver> stopObservers = new ArrayList<>();

    public GameThread(GameOfLife gameOfLife, long tickInterval) {
        this.gameOfLife = gameOfLife;
        this.tickInterval = tickInterval;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public long getTickInterval() {
        return tickInterval;
    }

    public void setTickInterval(long tickInterval) {
        this.tickInterval = tickInterval;
    }

    @Override
    public synchronized void run() {
        while (isRunning) {
            this.gameOfLife.doTick();

            try {
                Thread.sleep(tickInterval);
            } catch (InterruptedException exception) {
                isRunning = false;
            }
        }
        isRunning = false;

        notifyAllStopObservers();
    }

    public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        if (!isRunning) {
            return;
        }

        thread.interrupt();
    }

    @Override
    public void addStopObserver(StopObserver stopObserver) {
        this.stopObservers.add(stopObserver);
    }

    @Override
    public void removeStopObserver(StopObserver stopObserver) {
        this.stopObservers.remove(stopObserver);
    }

    @Override
    public void notifyAllStopObservers() {
        this.stopObservers.forEach( observer -> observer.onStopEvent(this));
    }
}
