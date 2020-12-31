package at.ac.univie.se2ws2020team0310.sketch_app.model.iterators;

public interface IterableCollection {

    public Iterator createIterator();

    public Object get(int index);

    public void add(Object item);

    public void clear();
}
