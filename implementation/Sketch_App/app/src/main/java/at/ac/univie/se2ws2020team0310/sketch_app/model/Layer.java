package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class Layer {

// Attributes

    private final List<GraphicalElement> drawnElements;

    private boolean visible;

// Constructors

    public Layer() {
        this.drawnElements = new ArrayList<>();
        this.visible = true;
    }

// Getters and Setters

    public boolean isEmpty() {
        return (drawnElements.size() <= 0);
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

    public void storeElement(GraphicalElement selectedGraphicalElement) {
        drawnElements.add(selectedGraphicalElement);
    }

    public GraphicalElement getLastElement() {
        GraphicalElement lastElement = null;
        try {
            lastElement = drawnElements.get(drawnElements.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastElement;
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
            Collections.rotate(drawnElements.subList(index, drawnElements.size()),-1);
        }
    };

    public void changeColor(int color) {
        try {
            getLastElement().setColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStrokeWidth(float strokewidth) {
        try {
            getLastElement().setStrokeWidth(strokewidth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeSize(float size) {
        try {
            getLastElement().setSize(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCoordinates(float x, float y) {
        try {
            getLastElement().setCoordinates(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeCoordinates(float x, float y) {
        try {
            getLastElement().changeCoordinates(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public void deleteLastElement() {
        drawnElements.remove(getLastElement());
    };




}