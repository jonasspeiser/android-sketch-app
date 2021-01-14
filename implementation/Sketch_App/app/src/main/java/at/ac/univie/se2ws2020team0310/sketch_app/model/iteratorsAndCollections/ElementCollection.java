package at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections;

import java.util.ArrayList;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class ElementCollection implements IterableCollection{

// Attributes
    ArrayList<GraphicalElement> collectedElements;

// Constructor

    public ElementCollection() {
        this.collectedElements = new ArrayList<>();
    }

// Other Methods

    @Override
    public GraphicalElement get(int index) {
        GraphicalElement item = collectedElements.get(index);
        return item;
    }

    @Override
    public void add(Object item) {
        collectedElements.add((GraphicalElement) item);
    }

    @Override
    public void clear() {
        collectedElements.clear();
    }

    @Override
    public Iterator createIterator() {
        return new ElementCollectionIterator(collectedElements);
    }

    @Override
    public int size() {
        return collectedElements.size();
    }

    @Override
    public void remove(int index) {
        collectedElements.remove(index);
    }

    @Override
    public void remove(Object item) {
        collectedElements.remove(item);
    }

    @Override
    public int indexOf(Object item) {
        return collectedElements.indexOf(item);
    }

    @Override
    public boolean contains(Object item) {
        return collectedElements.contains(item);
    }
}
