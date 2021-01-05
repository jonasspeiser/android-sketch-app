package at.ac.univie.se2ws2020team0310.sketch_app.model.iterators;

import java.util.ArrayList;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class ElementCollectionIterator implements Iterator{

    ArrayList<GraphicalElement> items;
    int position = 0;

    public ElementCollectionIterator(ArrayList<GraphicalElement> items) {
        this.items = items;
    }

    @Override
    public Object getNext() {
        Object item = items.get(position);
        position++;
        return item;
    }

    @Override
    public boolean hasMore() {
        return position < items.size();
    }
}
