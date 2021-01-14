package at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections;

import java.util.ArrayList;

public class IndexCollectionIterator implements Iterator{

    ArrayList<Integer> items;
    int position = 0;

    public IndexCollectionIterator(ArrayList<Integer> items) {
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
