package at.ac.univie.se2ws2020team0310.sketch_app.model.iterators;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public interface IterableCollection {

    Iterator createIterator();

    Object get(int index);

    void add(Object item);

    void clear();

    int size();

    void remove(int index);

    void remove(Object item);

    // TODO replace Exception with our own custom AppException (or define and use a new Exception)
    int indexOf(Object item) throws Exception;

    boolean contains(Object item);
}
