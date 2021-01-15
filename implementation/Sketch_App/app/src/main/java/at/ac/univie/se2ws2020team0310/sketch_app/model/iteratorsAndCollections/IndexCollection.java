package at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections;

import java.util.ArrayList;

public class IndexCollection implements IterableCollection {

    // Attributes
    private ArrayList<Integer> collectedIndices;

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
    public int size() {
        return collectedIndices.size();
    }

    @Override
    public void remove(int index) {
        collectedIndices.remove(index);
    }

    @Override
    public void remove(Object item) {
        collectedIndices.remove(item);
    }

    @Override
    public int indexOf(Object item) {
        return collectedIndices.indexOf(item);
    }

    @Override
    public boolean contains(Object item) {
        return collectedIndices.contains(item);
    }

    @Override
    public Iterator createIterator() {
        return new IndexCollectionIterator(collectedIndices);
    }
}
