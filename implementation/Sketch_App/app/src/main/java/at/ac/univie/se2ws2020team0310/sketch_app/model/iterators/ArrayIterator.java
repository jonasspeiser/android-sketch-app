package at.ac.univie.se2ws2020team0310.sketch_app.model.iterators;

public class ArrayIterator implements Iterator {

    Object[] items;
    int position = 0;

    public ArrayIterator(Object[] items) {
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
        return (position < items.length || items[position] == null);
    }
}
