package at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatterInterfaces;

public interface CustomObservable {
    public void registerObserver(CustomObserver observer);
    public void removeObserver(CustomObserver observer);
    public void notifyObservers();
}
