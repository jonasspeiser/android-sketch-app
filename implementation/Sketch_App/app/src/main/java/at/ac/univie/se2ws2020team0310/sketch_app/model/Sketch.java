package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class Sketch {

// Attributes

    private static final Sketch sketch = new Sketch();

    private final Layer[] layers;
    private Layer selectedLayer;

    private GraphicalElement selectedGraphicalElement;

    private boolean editModeTurnedOn;

// Constructor

    private Sketch() {
        this.layers = new Layer[3];
        for (int i = 0; i < 3; i++) {
            layers[i] = new Layer();
        }
        this.selectedLayer = layers[0];
    }

// Getters and Setters

    public static Sketch getSketch() {
        return sketch;
    }

    public Layer getSelectedLayer() {
        return this.selectedLayer;
    }

    public void setSelectedLayer(int layerNumber) {
        try {
            this.selectedLayer = layers[layerNumber];
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
        for (Layer layer : layers) {
            if (layer.isVisible()) {
                visibleElements.addAll(layer.getDrawnElements());
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
        layers[layerNumber].setVisible(isVisible);
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

    public void changeCoordinates(float x, float y) {
        if(!isEditModeTurnedOn()) {
            this.getSelectedLayer().changeCoordinates(x, y);
        }
    }

    public void deleteElement() {
        selectedLayer.deleteElement();
    }

    public void clear() {
        for (Layer layer : layers) {
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
}


