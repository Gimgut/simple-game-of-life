public interface TickObservable {

    void addTickObserver(TickObserver tickObserver);
    void removeTickObserver(TickObserver tickObserver);
    void notifyAllTickObservers();
}
