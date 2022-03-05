public interface GameboardObservable {

    void addGameboardObserver(GameboardObserver gameboardObserver);
    void removeGameboardObserver(GameboardObserver gameboardObserver);
    void notifyAllGameboardObservers();
}
