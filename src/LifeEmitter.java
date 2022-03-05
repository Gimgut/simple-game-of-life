public interface LifeEmitter {

    void addLifeObserver(LifeObserver lifeObserver);
    void removeLifeObserver(LifeObserver lifeObserver);
    void notifyAllLifeObservers(int widthPos, int heightPos);
}
