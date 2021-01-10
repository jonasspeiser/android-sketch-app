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
import at.ac.univie.se2ws2020team0310.sketch_app.model.export.Export;
import at.ac.univie.se2ws2020team0310.sketch_app.model.export.ExportJPEG;
import at.ac.univie.se2ws2020team0310.sketch_app.model.export.ExportPNG;
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
    private int selectedLayerIndex;

    private GraphicalElement selectedGraphicalElement;

    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

    private boolean editModeTurnedOn;

// Constructor

    private Sketch() {
        this.observers = new ArrayList<>();
        this.layers = new LayerCollection();
        this.selectedLayerIndex = 0;
        this.selectedColor = Color.BLACK;
        this.selectedSize = 150;
        this.selectedStrokeWidth = 15;
    }

// Getters and Setters

    public void setLayers(IterableCollection layers) {
        this.layers = layers;
        notifyObservers();
    }

    public static Sketch getSketch() {
        return sketch;
    }

    public int getSelectedLayerIndex() {
        return selectedLayerIndex;
    }
    public void setSelectedLayerIndex(int layerNumber) {
        this.selectedLayerIndex = layerNumber;
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
                while (elementsIterator.hasMore()) {
                    visibleElements.add((GraphicalElement) elementsIterator.getNext());
                }
            }
        }
        return visibleElements;
    }

    public boolean isEditModeTurnedOn() {
        return editModeTurnedOn;
    }

    public void setEditModeTurnedOn(boolean editModeTurnedOn) {
        this.editModeTurnedOn = editModeTurnedOn;
    }


// Other Methods

    public Layer getSelectedLayer() {
        return ((Layer) layers.get(selectedLayerIndex));
    }

    public void setLayerVisibility(int layerNumber, boolean isVisible) {
        Layer layer = (Layer) layers.get(layerNumber);
        layer.setVisible(isVisible);
        notifyObservers();
    }

    public boolean layerIsEmpty() {
        return getSelectedLayer().isEmpty();
    }

    public void storeElement() {
        getSelectedLayer().storeElement(this.getSelectedGraphicalElement());
        notifyObservers();
    }

    public void changeColor(int color) {
        getSelectedLayer().changeColor(color);
        notifyObservers();
    }

    public void changeStrokeWidth(float strokewidth) {
        getSelectedLayer().changeStrokeWidth(strokewidth);
        notifyObservers();
    }

    public void changeSize(int size) {
        getSelectedLayer().changeSize(size);
        notifyObservers();
    }

    //TODO: Entscheidungslogik, ob set oder changeCoordinates aufgerufen wird, hier herein (eigene Methode, abhängig von isEditModeOn)

    public void setCoordinates(float x, float y) {
        getSelectedLayer().setCoordinates(x, y);
        notifyObservers();
    }

    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        if (!isEditModeTurnedOn()) {
            getSelectedLayer().changeCoordinates(x, y, lastTouchX, lastTouchY);
            notifyObservers();
        }
    }

    public void deleteElement() {
        getSelectedLayer().deleteElement();
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
     *
     * @param x
     * @param y
     * @return returns true if there is an Element at the given coordinates
     */
    public boolean isWithinElement(float x, float y) {  // TODO: Rename Method!
        if (isEditModeTurnedOn()) {
            return getSelectedLayer().makeElementOnPositionEditable(x, y);
        } else {
            getSelectedLayer().resetEditableElements();
            return getSelectedLayer().makeElementOnPositionMovable(x, y);
        }
    }

    public void selectGraphicalElement(EGraphicalElementType type) {
        try {
            getSelectedLayer().resetEditableElements();
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(type, this.selectedColor, this.selectedSize, this.selectedStrokeWidth));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    //Use Template Method Pattern for division of file exporting into similar and differing parts
    public boolean export(Context context, String fileFormat, Bitmap drawingCache) throws IOException {

        if(fileFormat=="JPEG"){
            Export exportJPEG = new ExportJPEG(context,fileFormat,drawingCache);
            exportJPEG.exportImage(context,drawingCache,fileFormat);
        } else {
            Export exportPNG = new ExportPNG(context,fileFormat,drawingCache);
            exportPNG.exportImage(context, drawingCache, fileFormat);
        }
        return true;
    }

//    public boolean exportJPEG(ContentResolver contentResolver, Bitmap DrawingCache) {
//        if(MediaStore.Images.Media.insertImage(contentResolver,DrawingCache, UUID.randomUUID().toString()+".png","drawing")!=null){
//            return true;
//        } else {
//            return false;
//        }
//    }


    /**
     * Serializes the current layers and saves them to a file
     * <p>
     * Based on: https://stackoverflow.com/questions/14981233/android-arraylist-of-custom-objects-save-to-sharedpreferences-serializable/15011927#15011927
     * in combination with: https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
     *
     * @param context The application context (activity.getApplicationContext())
     * @param saveslot A number between 1 and 5
     */
    public void saveLayersToFile(Context context, int saveslot) {

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        //Create our gson instance
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IterableCollection.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(GraphicalElement.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(DrawStrategy.class, new GsonInterfaceAdapter());
        Gson gson = builder.create();

        String filename = "SavedSketch" + saveslot;
        String json = gson.toJson(this.layers);
        prefsEditor.putString(filename, json);
        prefsEditor.commit();
    }


    /**
     * Restores saved layers by reading them from a file and overwrites the current layers with their content
     * <p>
     * Based on: https://stackoverflow.com/questions/14981233/android-arraylist-of-custom-objects-save-to-sharedpreferences-serializable/15011927#15011927
     * in combination with: https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
     *
     * @param context The application context (activity.getApplicationContext())
     * @param saveslot A number between 1 and 5
     * @throws NullPointerException When trying to load a non-existent file
     */
    public void loadLayersFromFile(Context context, int saveslot) throws NullPointerException {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);

        //Create our gson instance
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IterableCollection.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(GraphicalElement.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(DrawStrategy.class, new GsonInterfaceAdapter());
        Gson gson = builder.create();

        String json = appSharedPrefs.getString("SavedSketch" + saveslot, "");

        if(json == null || json.isEmpty()){
            throw new NullPointerException("Called saveslot is empty");
        } else {
            LayerCollection storedLayers = gson.fromJson(json, LayerCollection.class);
            setLayers(storedLayers);
        }
    }


    // as seen at: https://stackoverflow.com/questions/6125296/delete-sharedpreferences-file
    public void deleteSavedSketches(Context context){
        File dir = new File(context.getFilesDir().getParent() + "/shared_prefs/");
        String[] children = dir.list();
        for (String child : children) {
            // clear each preference file
            context.getSharedPreferences(child.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
            //delete the file
            new File(dir, child).delete();
        }
    }


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
        if (i >= 0) {
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




