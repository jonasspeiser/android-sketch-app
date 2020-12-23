package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Paint;
import android.util.Log;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class Sketch {

// Attributes

    private Layer layers[];
    private Layer selectedLayer;

    private GraphicalElement selectedGraphicalElement;

// Constructor

    public Sketch() {
        this.layers = new Layer[3];
        for (int i = 0; i < 3; i++) {
            layers[i] = new Layer();
        }
        this.selectedLayer = layers[0];
    }

// Getters and Setters

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

    public void storeElement() {
        this.getSelectedLayer().storeElement(this.getSelectedGraphicalElement());
    }

    public void changeColor(int color) {
        this.getSelectedLayer().changeColor(color);
    }

    public void changeStrokeWidth(float strokewidth) {
        this.getSelectedLayer().changeStrokeWidth(strokewidth);
    }

    public void changeTextSize(int textsize) {
        this.getSelectedLayer().changeTextSize(textsize);
    }

    public void changeCoordinates(float x, float y) {
        this.getSelectedLayer().changeCoordinates(x, y);
    }

    public void clear() {
        for (Layer layer : layers) {
            layer.clear();
        }
    }

    public void selectLine(Paint selectedPaint) {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.LINE, selectedPaint));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectCircle(Paint selectedPaint) {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.CIRCLE, selectedPaint));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public void selectQuadrangle(Paint selectedPaint) {

        // use a Factory to create the Quadrangle as a GraphicalElement
        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.QUADRANGLE, selectedPaint));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public void selectTriangle(Paint selectedPaint) {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.TRIANGLE, selectedPaint));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectText(Paint selectedPaint) {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.TEXT_FIELD, selectedPaint));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

}
