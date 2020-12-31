package at.ac.univie.se2ws2020team0310.sketch_app.model.iterators;

import java.util.ArrayList;

public class IndexCollection implements IterableCollection {
// Attributes
    ArrayList<Integer> collectedIndices;

// Constructor

    public IndexCollection() {
        this.collectedIndices = new ArrayList<>();
    }

// Other Methods

    @Override
    public Integer get(int index) {
        int item = collectedIndices.get(index);
        return item;
    }

    @Override
    public void add(Object item) {
        collectedIndices.add((int) item);
    }

    @Override
    public void clear() {
        collectedIndices.clear();
    }

    @Override
    public Iterator createIterator() {
        return new IndexCollectionIterator(collectedIndices);
    }
}
