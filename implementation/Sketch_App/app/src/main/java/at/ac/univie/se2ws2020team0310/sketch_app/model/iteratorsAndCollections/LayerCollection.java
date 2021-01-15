package at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections;

import android.util.Log;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Layer;

public class LayerCollection implements IterableCollection {

// Attributes

    private static final int MAX_LAYERS = 3;
    private Layer[] layers;

// Constructor

    public LayerCollection() {
        this.layers = new Layer[MAX_LAYERS];
        for (int i = 0; i < MAX_LAYERS; i++) {
            layers[i] = new Layer();
        }
    }

    @Override
    public Iterator createIterator() {
        return new LayerCollectionIterator(layers);
    }

    @Override
    public Object get(int index) {
        return layers[index];
    }

    @Override
    public void add(Object item) {
        Log.e("LayerCollection", "It is not possible to add new Layers");
    }

    @Override
    public void clear() {
        Log.e("LayerCollection", "It is not possible to remove a Layer");
    }

    @Override
    public int size() {
        return layers.length;
    }

    @Override
    public void remove(int index) {
        Log.e("LayerCollection", "It is not possible to remove a Layer");
    }

    @Override
    public void remove(Object item) {
        Log.e("LayerCollection", "It is not possible to remove a Layer");
    }

    @Override
    public int indexOf(Object item) throws Exception {
        int index = 0;
        Iterator iterator = createIterator();
        while (iterator.hasMore()) {
            if (item.equals(iterator.getNext())) {
                return index;
            }
            index++;
        }
        throw new Exception(
                "No index can be returned: There is no such object in LayerCollection.");
    }

    @Override
    public boolean contains(Object item) {
        boolean flag = false;
        Iterator iterator = createIterator();
        while (iterator.hasMore()) {
            if (item.equals(iterator.getNext())) {
                flag = true;
            }
        }
        return flag;
    }
}
