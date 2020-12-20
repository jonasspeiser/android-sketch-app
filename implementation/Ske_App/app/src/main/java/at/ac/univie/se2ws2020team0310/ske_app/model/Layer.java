package at.ac.univie.se2ws2020team0310.ske_app.model;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Layer {
// Attributes

    private GraphicalElement selectedGraphicalElement;

    private final List<GraphicalElement> drawnElements;

    private boolean visible;

// Constructors

    public Layer() {
        this.drawnElements = new ArrayList<>();
    }

// Getters and Setters

    public GraphicalElement getSelectedGraphicalElement() {
        return selectedGraphicalElement;
    }

    public void setSelectedGraphicalElement(GraphicalElement selectedGraphicalElement) {
        this.selectedGraphicalElement = selectedGraphicalElement;
    }

    public List<GraphicalElement> getDrawnElements() {
        return drawnElements;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

// Other Methods

    public void storeElement() {
        drawnElements.add(selectedGraphicalElement);
    }

    public GraphicalElement getLastElement() {
        return drawnElements.get(drawnElements.size() - 1);
    }

    public void clear() {
        drawnElements.clear();
    }

    public void editElement(GraphicalElement graphicalElement) {
        // TODO: Hier muss statt des if noch ein try-catch Block mit eigener Exception her ("Element not found")
        if (drawnElements.contains(graphicalElement)) {
            // moves the element which was given as a parameter to the last index in the list
            // it can now be edited, as the last List element will always be edited with user input
            int index = drawnElements.indexOf(graphicalElement);
            Collections.rotate(drawnElements.subList(index, -1),-1);
        }
    };

    public void changeColor(int color) {
        getLastElement().setColor(color);
    }

    public void changeStrokeWidth(float strokewidth) {
        getLastElement().setStrokeWidth(strokewidth);
    }

    public void changeTextSize(float textsize) {
        getLastElement().setTextSize(textsize);
    }

    public void changeCoordinates(float x, float y) {
        getLastElement().setCoordinates(x, y);
    };

    public void deleteLastElement() {
        drawnElements.remove(getLastElement());
    };



    public void selectLine() {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.LINE));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectCircle() {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.CIRCLE));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public void selectQuadrangle() {

        // use a Factory to create the Quadrangle as a GraphicalElement
        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.QUADRANGLE));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public void selectTriangle() {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.TRIANGLE));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectText() {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.TEXT_FIELD));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }
}
