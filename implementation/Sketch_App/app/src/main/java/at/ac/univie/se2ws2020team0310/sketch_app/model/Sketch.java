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

// Other Methods

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
        this.getSelectedLayer().changeCoordinates(x, y);
    }

    public void deleteElement() {
        selectedLayer.deleteLastElement();
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
     * Checks, whether the provided coordinates are within a graphical element of the selected layer and if so, makes that element editable.
     *
     * @param x value on the x-axes
     * @param y value on the y-axes
     * @return returns true if the provided coordinates are within an element
     */
    public boolean isWithinElement(float x, float y) {
            return selectedLayer.isWithinElement(x, y);
        }

    public void selectGraphicalElement(EGraphicalElementType type, int color, float size, float strokeWidth) {
        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(type, color, size, strokeWidth));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }
}


