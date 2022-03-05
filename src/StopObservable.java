public interface StopObservable {

    void addStopObserver(StopObserver stopObserver);
    void removeStopObserver(StopObserver stopObserver);
    void notifyAllStopObservers();

}
