package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.IterableCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.Iterator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.LayerCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.LayerCollectionIterator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.storage.GsonInterfaceAdapter;

public class Sketch {

// Attributes

    private static final Sketch sketch = new Sketch();

    private IterableCollection layers;
    private Layer selectedLayer;

    private GraphicalElement selectedGraphicalElement;

    private boolean editModeTurnedOn;

// Constructor

    private Sketch() {
        this.layers = new LayerCollection();
        this.selectedLayer = (Layer) layers.get(0);
    }

// Getters and Setters

    public void setLayers(IterableCollection layers) {
        this.layers = layers;
    }

    public static Sketch getSketch() {
        return sketch;
    }

    public Layer getSelectedLayer() {
        return this.selectedLayer;
    }

    public void setSelectedLayer(int layerNumber) {
        try {
            this.selectedLayer = (Layer) layers.get(layerNumber);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            Log.e("Sketch", e.getMessage());
        }
    }

    public GraphicalElement getSelectedGraphicalElement() {
        return selectedGraphicalElement;
    }

    public void setSelectedGraphicalElement(GraphicalElement selectedGraphicalElement) {
        this.selectedGraphicalElement = selectedGraphicalElement;
    }

    public List<GraphicalElement> getDrawnElements() {
        List<GraphicalElement> visibleElements = new ArrayList<>();
        Iterator layersIterator = layers.createIterator();
        while (layersIterator.hasMore()) {
            Layer layer = (Layer) layersIterator.getNext();
            if (layer.isVisible()) {
                Iterator elementsIterator = layer.createIterator();
                while(elementsIterator.hasMore()) {
                    visibleElements.add((GraphicalElement) elementsIterator.getNext());
                }
            }
        }
        return  visibleElements;
    }

    public boolean isEditModeTurnedOn() {
        return editModeTurnedOn;
    }

    public void setEditModeTurnedOn(boolean editModeTurnedOn) {
        this.editModeTurnedOn = editModeTurnedOn;
    }


// Other Methods

    public void setLayerVisibility(int layerNumber, boolean isVisible) {
        Layer layer = (Layer) layers.get(layerNumber);
        layer.setVisible(isVisible);
    }

    public boolean layerIsEmpty() {
        return selectedLayer.isEmpty();
    }

    public void storeElement() {
        this.getSelectedLayer().storeElement(this.getSelectedGraphicalElement());
    }

    public void changeColor(int color) {
        this.getSelectedLayer().changeColor(color);
    }

    public void changeStrokeWidth(float strokewidth) {
        this.getSelectedLayer().changeStrokeWidth(strokewidth);
    }

    public void changeSize(int size) {
        this.getSelectedLayer().changeSize(size);
    }

    public void setCoordinates(float x, float y) {
        this.getSelectedLayer().setCoordinates(x, y);
    }

    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        if(!isEditModeTurnedOn()) {
            this.getSelectedLayer().changeCoordinates(x, y, lastTouchX, lastTouchY);
        }
    }

    public void deleteElement() {
        selectedLayer.deleteElement();
    }

    public void clear() {
        Iterator layersIterator = layers.createIterator();
        while (layersIterator.hasMore()) {
            Layer layer = (Layer) layersIterator.getNext();
            layer.clear();
        }
    }

    public void resetSelection() {
        this.setSelectedGraphicalElement(null);
    }

    /**
     * Checks, wether there is an Element on touch position.
     * When Edit Mode is on, adds that Element to the User-Selection of editable Elements.
     * Else let's user move that element.
     * @param x
     * @param y
     * @return returns true if there is an Element at the given coordinates
     */
    public boolean isWithinElement(float x, float y) {  // TODO: Rename Method!
        if (isEditModeTurnedOn()) {
            return selectedLayer.makeElementOnPositionEditable(x, y);
        } else {
            selectedLayer.resetEditableElements();
            return selectedLayer.makeElementOnPositionMovable(x, y);
        }
    }

    public void selectGraphicalElement(EGraphicalElementType type, int color, float size, float strokeWidth) {
        try {
            selectedLayer.resetEditableElements();
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(type, color, size, strokeWidth));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public boolean exportPNG(Context context,ContentResolver contentResolver,Bitmap DrawingCache) throws IOException{
        FileOutputStream fileOutputStream=context.openFileOutput("YourFileName.jpg",Context.MODE_PRIVATE);
        DrawingCache.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
        fileOutputStream.close();
        return true;
    }

    public boolean exportJPEG(Context context,ContentResolver contentResolver,Bitmap DrawingCache) throws IOException {
        FileOutputStream fileOutputStream=context.openFileOutput("YourFileName.jpg",Context.MODE_PRIVATE);
        DrawingCache.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
        fileOutputStream.close();
        return true;
    }

//    public boolean exportJPEG(ContentResolver contentResolver, Bitmap DrawingCache) {
//        if(MediaStore.Images.Media.insertImage(contentResolver,DrawingCache, UUID.randomUUID().toString()+".png","drawing")!=null){
//            return true;
//        } else {
//            return false;
//        }
//    }

    // Constant with a file name
    public static String fileName = "MyObject";

    /** Serializes the current sketch object and saves it to a file
     *
     * Based on: https://stackoverflow.com/questions/14981233/android-arraylist-of-custom-objects-save-to-sharedpreferences-serializable/15011927#15011927
     * in combination with: https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
     *
     * @param context The application context (activity.getApplicationContext())
     */
    public void saveToFile(Context context) {

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        //Create our gson instance
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IterableCollection.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(GraphicalElement.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(DrawStrategy.class, new GsonInterfaceAdapter());
        Gson gson = builder.create();

        String json = gson.toJson(this);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }


    /** Creates a sketch object by reading it from a file
     *
     * Based on: https://stackoverflow.com/questions/14981233/android-arraylist-of-custom-objects-save-to-sharedpreferences-serializable/15011927#15011927
     * in combination with: https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
     *
     * @param context The application context (activity.getApplicationContext())
     * @return The stored sketch object
     */
    public static Sketch readFromFile(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);

        //Create our gson instance
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IterableCollection.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(GraphicalElement.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(DrawStrategy.class, new GsonInterfaceAdapter());
        Gson gson = builder.create();

        String json = appSharedPrefs.getString("MyObject", "");
        Sketch storedSketch = gson.fromJson(json, Sketch.class);

        return storedSketch;
    }
/*
    public Gson registerGsonAdapter() {
        //Create our gson instance
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IterableCollection.class, new GsonInterfaceAdapter());
        Gson gson = builder.create();
        return gson;
    }

 */
}




