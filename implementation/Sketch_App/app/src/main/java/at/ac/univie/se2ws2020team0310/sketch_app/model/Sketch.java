package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.ElementNotFoundException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.IDrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.export.Export;
import at.ac.univie.se2ws2020team0310.sketch_app.model.export.ExportJPEG;
import at.ac.univie.se2ws2020team0310.sketch_app.model.export.ExportPNG;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections.IterableCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections.Iterator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections.LayerCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatternInterfaces.CustomObservable;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatternInterfaces.CustomObserver;
import at.ac.univie.se2ws2020team0310.sketch_app.model.storage.GsonInterfaceAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sketch implements CustomObservable {

// Attributes

    private static final Sketch sketch = new Sketch();

    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

    private ArrayList<CustomObserver> observers;

    private IterableCollection layers;
    private int selectedLayerIndex;

    private GraphicalElement selectedGraphicalElement;
    private CombinedShape currentCombinedShape;



    private boolean editModeTurnedOn;
    private boolean combineShapesModeOn;

// Constructor

    private Sketch() {
        this.observers = new ArrayList<>();
        this.layers = new LayerCollection();
        this.selectedLayerIndex = 0;
        this.selectedColor = Color.BLACK;
        this.selectedSize = 150;
        this.selectedStrokeWidth = 15;
        this.currentCombinedShape = null;
        this.combineShapesModeOn = false;
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

    public void toggleEditMode() {
        if (!isCombineShapesModeOn()) {
            this.setEditModeTurnedOn(!this.editModeTurnedOn);
        }
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

    /**
     * Change Element coordinates means move the Element to a new Position Occurs only if Edit Mode
     * is turned off
     *
     * @param x          coordinate x
     * @param y          coordinate y
     * @param lastTouchX coordinate x of last touch position
     * @param lastTouchY coordinate y of last touch position
     */
    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        if (!isEditModeTurnedOn()) {
            getSelectedLayer().changeCoordinates(x, y, lastTouchX, lastTouchY);
            notifyObservers();
        }
    }

    public void deleteElement() {
        getSelectedLayer().deleteEditableElements();
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
     * Checks, whether there is an Element on touch position. When Edit Mode is on, adds that
     * Element to the User-Selection of editable Elements. Else let's user move that element.
     *
     * @param x coordinate x
     * @param y coordinate y
     * @return returns true if there is an Element at the given coordinates
     */
    public boolean isWithinElement(float x, float y) throws AppException {  // TODO: Rename Method!
        if (isCombineShapesModeOn() && getCurrentCombinedShape() != null) {
            return getSelectedLayer().addElementToCombinedShape(x, y, getCurrentCombinedShape());
        } else if (isEditModeTurnedOn()) {
            return getSelectedLayer().makeElementOnPositionEditable(x, y);
        } else {
            getSelectedLayer().resetEditableElements();
            return getSelectedLayer().makeElementOnPositionMovable(x, y);
        }
    }

    /**
     * Create and select a new GraphicalElement with the given type
     *
     * @param type the type of the GraphicalElement
     */
    public void selectGraphicalElement(EGraphicalElementType type) {
        try {
            getSelectedLayer().resetEditableElements();
            this.setSelectedGraphicalElement(GraphicalElementFactory
                    .createElement(type, this.selectedColor, this.selectedSize,
                            this.selectedStrokeWidth));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }


    /**
     * Create and select a new GraphicalElement as a copy of the given one as parameter
     *
     * @param element the element to copy from
     */
    public void selectGraphicalElement(GraphicalElement element) {
        getSelectedLayer().resetEditableElements();
        setSelectedGraphicalElement(GraphicalElementFactory.createElement(element));
    }

    public void selectBold() {
        GraphicalElement boldText = GraphicalElementFactory
                .createBoldText(getSelectedGraphicalElement());
        setSelectedGraphicalElement(boldText);
    }

    public void selectItalic() {
        GraphicalElement italicText = GraphicalElementFactory
                .createItalicText(getSelectedGraphicalElement());
        setSelectedGraphicalElement(italicText);
    }

    public void selectUnderline() {
        GraphicalElement underlineText = GraphicalElementFactory
                .createUnderlineText(getSelectedGraphicalElement());
        setSelectedGraphicalElement(underlineText);
    }

    //Use Template Method Pattern for division of file exporting into similar and differing parts
    public boolean export(Context context, String fileFormat, Bitmap drawingCache)
            throws IOException {

        if (fileFormat.equals("JPEG")) {
            Export exportJPEG = new ExportJPEG(context, fileFormat, drawingCache);
            exportJPEG.exportImage(context, drawingCache, fileFormat);
        } else {
            Export exportPNG = new ExportPNG(context, fileFormat, drawingCache);
            exportPNG.exportImage(context, drawingCache, fileFormat);
        }
        return true;
    }

    /**
     * Serializes the current layers and saves them to a file
     * <p>
     * Based on: https://stackoverflow.com/questions/14981233/android-arraylist-of-custom-objects-save-to-sharedpreferences-serializable/15011927#15011927
     * in combination with: https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
     *
     * @param context  The application context (activity.getApplicationContext())
     * @param saveslot A number between 1 and 5
     */
    public void saveLayersToFile(Context context, int saveslot) {
        // Get SharedPreferences instance
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        // Create gson instance
        Gson gson = registerGsonAdapter();
        // Serialize layers collection object and store it in SharedPreferences
        String filename = "SavedSketch" + saveslot;
        String json = gson.toJson(this.layers);
        prefsEditor.putString(filename, json);
        prefsEditor.apply();
    }


    /**
     * Restores saved layers by reading them from a file and overwrites the current layers with
     * their content
     * <p>
     * based on: https://stackoverflow.com/questions/14981233/android-arraylist-of-custom-objects-save-to-sharedpreferences-serializable/15011927#15011927
     * in combination with: https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
     *
     * @param context  The application context (activity.getApplicationContext())
     * @param saveslot A number between 1 and 5, matching the saveslot the desired object was saved to
     * @throws NullPointerException When trying to load from an empty saveslot
     */
    public void loadLayersFromFile(Context context, int saveslot) throws NullPointerException {
        // Get SharedPreferences instance
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        // Create gson instance
        Gson gson = registerGsonAdapter();
        // deserialize json to layers collection object from SharedPreferences
        String json = appSharedPrefs.getString("SavedSketch" + saveslot, "");
        if (json == null || json.isEmpty()) {
            throw new NullPointerException("Called saveslot is empty");
        } else {
            LayerCollection storedLayers = gson.fromJson(json, LayerCollection.class);
            setLayers(storedLayers);
        }
    }

    /**
     * Needed for saving and loading sketches on the device.
     * Registers Gson Adapters, needed for serialization and deserialization of our layers objects.
     * @return a Gson instance to work with for (de-)serialization of layers objects
     */
    public Gson registerGsonAdapter() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IterableCollection.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(GraphicalElement.class, new GsonInterfaceAdapter());
        builder.registerTypeAdapter(IDrawStrategy.class, new GsonInterfaceAdapter());
        return builder.create();
    }

    /**
     * Deletes all the saved sketches
     * based on: https://stackoverflow.com/questions/6125296/delete-sharedpreferences-file
     * @param context The application context (activity.getApplicationContext())
     */
    public void deleteSavedSketches(Context context) {
        File dir = new File(context.getFilesDir().getParent() + "/shared_prefs/");
        String[] children = dir.list();
        for (String child : children) {
            // clear each preference file
            context.getSharedPreferences(child.replace(".xml", ""), Context.MODE_PRIVATE).edit()
                    .clear().apply();
            //delete the file
            new File(dir, child).delete();
        }
    }

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

    public CombinedShape getCurrentCombinedShape() {
        return currentCombinedShape;
    }

    public void setCurrentCombinedShape(CombinedShape currentCombinedShape) {
        this.currentCombinedShape = currentCombinedShape;
    }

    public boolean isCombineShapesModeOn() {
        return combineShapesModeOn;
    }

    public void setCombineShapesModeOn(boolean combineShapesModeOn) {
        this.combineShapesModeOn = combineShapesModeOn;
    }

    /**
     * Remove the given GraphicalElement from the Sketch, by searching it through its Layers
     *
     * @param graphicalElement the element to remove
     * @throws ElementNotFoundException if the element was not found in any Layer
     */
    public void removeElement(GraphicalElement graphicalElement) throws ElementNotFoundException {
        Iterator layersIterator = layers.createIterator();
        boolean foundElement = false;
        while (layersIterator.hasMore()) {
            Layer layer = (Layer) layersIterator.getNext();
            // check if the Layer contains the element, then remove it
            if (layer.containsElement(graphicalElement)) {
                foundElement = true;
                layer.removeElement(graphicalElement);
                break;
            }
        }
        if (!foundElement) {
            throw new ElementNotFoundException(graphicalElement);
        }
    }

    /**
     * Remove Graphical Elements from Sketch
     *
     * @param elements elements to remove
     */
    public void removeElements(List<GraphicalElement> elements) {
        for (GraphicalElement element : elements) {
            try {
                removeElement(element);
            } catch (ElementNotFoundException e) {
                Log.w("Sketch", "Cannot remove element from Sketch: " + e.getLocalizedMessage());
            }
        }
    }
}




