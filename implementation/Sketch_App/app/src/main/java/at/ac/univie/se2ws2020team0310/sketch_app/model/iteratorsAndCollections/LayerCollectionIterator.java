package at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Layer;

public class LayerCollectionIterator implements Iterator {

    private Layer[] items;
    private int position = 0;

    public LayerCollectionIterator(Layer[] items) {
        this.items = items;
    }

    @Override
    public Object getNext() {
        Object item = items[position];
        position++;
        return item;
    }

    @Override
    public boolean hasMore() {
        return (position < (items.length) && items[position] != null);
    }
}
