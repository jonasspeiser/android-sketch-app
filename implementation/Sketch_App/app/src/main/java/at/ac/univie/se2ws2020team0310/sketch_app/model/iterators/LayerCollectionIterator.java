package at.ac.univie.se2ws2020team0310.sketch_app.model.iterators;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Layer;

public class LayerCollectionIterator implements Iterator {

    Layer[] items;
    int position = 0;

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
        return (position < (items.length - 1) || items[position] == null);
    }
}
