package at.ac.univie.se2ws2020team0310.sketch_app.model.iterators;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public interface IterableCollection {

    public Iterator createIterator();

    public Object get(int index);

    public void add(Object item);

    public void clear();

    public int size();

    public void remove(int index);

    public void remove(Object item);

    public int indexOf(Object item) throws Exception;

    public boolean contains(Object item);
}
