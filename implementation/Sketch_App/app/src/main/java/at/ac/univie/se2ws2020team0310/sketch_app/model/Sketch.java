package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.IterableCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.Iterator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.LayerCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatterInterfaces.CustomObservable;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatterInterfaces.CustomObserver;
import at.ac.univie.se2ws2020team0310.sketch_app.model.storage.GsonInterfaceAdapter;

public class Sketch implements CustomObservable {

// Attributes

    private static final Sketch sketch = new Sketch();

    private ArrayList<CustomObserver> observers;

    private IterableCollection layers;
    private Layer selectedLayer; // TODO: int statt Layerobjekt

    private GraphicalElement selectedGraphicalElement;

    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

    private boolean editModeTurnedOn;

// Constructor

    private Sketch() {
        this.observers = new ArrayList<>();
        this.layers = new LayerCollection();
        this.selectedLayer = (Layer) layers.get(0);
        this.selectedColor = Color.BLACK;
        this.selectedSize = 150;
        this.selectedStrokeWidth = 15;
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

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public float getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(float selectedSize) {
        this.selectedSize = selectedSize;
    }

    public float getSelectedStrokeWidth() {
        return selectedStrokeWidth;
    }

    public void setSelectedStrokeWidth(float selectedStrokeWidth) {
        this.selectedStrokeWidth = selectedStrokeWidth;
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
        notifyObservers();
    }

    public boolean layerIsEmpty() {
        return selectedLayer.isEmpty();
    }

    public void storeElement() {
        this.getSelectedLayer().storeElement(this.getSelectedGraphicalElement());
        notifyObservers();
    }

    public void changeColor(int color) {
        this.getSelectedLayer().changeColor(color);
        notifyObservers();
    }

    public void changeStrokeWidth(float strokewidth) {
        this.getSelectedLayer().changeStrokeWidth(strokewidth);
        notifyObservers();
    }

    public void changeSize(int size) {
        this.getSelectedLayer().changeSize(size);
        notifyObservers();
    }

    //TODO: Entscheidungslogik, ob set oder changeCoordinates aufgerufen wird, hier herein (eigene Methode, abhängig von isEditModeOn)

    public void setCoordinates(float x, float y) {
        this.getSelectedLayer().setCoordinates(x, y);
        notifyObservers();
    }

    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        if(!isEditModeTurnedOn()) {
            this.getSelectedLayer().changeCoordinates(x, y, lastTouchX, lastTouchY);
            notifyObservers();
        }
    }

    public void deleteElement() {
        selectedLayer.deleteElement();
        notifyObservers();
    }

    public void clear() {
        Iterator layersIterator = layers.createIterator();
        while (layersIterator.hasMore()) {
            Layer layer = (Layer) layersIterator.getNext();
            layer.clear();
        }
        notifyObservers();
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

    public void selectGraphicalElement(EGraphicalElementType type) {
        try {
            selectedLayer.resetEditableElements();
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(type, this.selectedColor, this.selectedSize, this.selectedStrokeWidth));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    //Use Template Method Pattern for division of file exporting into similar and differing parts
    public boolean export(Context context, ContentResolver contentResolver, String fileFormat, Bitmap drawingCache) throws IOException {

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File saveImage = new File(path,(System.currentTimeMillis() + "." + fileFormat));
        saveImage.createNewFile();  /// <--- add this line
        FileOutputStream out = new FileOutputStream(saveImage);
        if(fileFormat=="JPEG") {
            drawingCache.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } else {
            drawingCache.compress(Bitmap.CompressFormat.PNG, 100, out);
        }
        out.close();
        Log.d("Export","Export in " + fileFormat + " successful.");
        MediaScannerConnection.scanFile(context, new String[]{saveImage.getPath()}, null, null);
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




    //TODO: layers speichern, nicht ganzen Sketch
    //TODO : Creating gson instance auslagern (in eigene Methode, evtl in andere Klasse?)


/*
    public Gson registerGsonAdapter() {
        //Create our gson instance
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IterableCollection.class, new GsonInterfaceAdapter());
        Gson gson = builder.create();
        return gson;
    }

 */

    @Override
    public void registerObserver(CustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(CustomObserver observer) {
        int i = observers.indexOf(observer);
        if(i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (CustomObserver observer : observers) {
            observer.update();
        }
    }

}




