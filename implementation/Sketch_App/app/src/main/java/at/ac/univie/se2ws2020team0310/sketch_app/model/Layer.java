package at.ac.univie.se2ws2020team0310.sketch_app.model;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class Layer {

// Attributes

    private final List<GraphicalElement> drawnElements;
    private final List<Integer> editableElementsIndices;

    private boolean visible;

// Constructors

    public Layer() {
        this.drawnElements = new ArrayList<>();
        this.editableElementsIndices = new ArrayList<>();
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
/*
    public GraphicalElement getLastElement() {
        GraphicalElement lastElement = null;
        try {
            lastElement = drawnElements.get(drawnElements.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastElement;
    }

 */

    public void clear() {
        drawnElements.clear();
    }



    /**
     * Checks, whether the provided coordinates are within a graphical element and if so, makes that element editable.
     *
     * @param x value on the x-axes
     * @param y value on the y-axes
     * @return returns true if the provided coordinates are within an already drawn element
     */
    public boolean isWithinElement(float x, float y) {
        // TODO: We need a better name, as this method not only checks, whether a touch falls within an element, but also sets the found element "editable"
        for (GraphicalElement graphicalElement : getDrawnElements()) {
            if (graphicalElement.isWithinElement(x, y)) {
                editElement(graphicalElement);
                return true;
            }
        }
        return false;
    }


    // TODO: Change methods from here on downwards

    public void editElement(GraphicalElement graphicalElement) {
        // TODO: Hier muss statt des if noch ein try-catch Block mit eigener Exception her ("Element not found")
        if (drawnElements.contains(graphicalElement)) {
            int index = drawnElements.indexOf(graphicalElement);
            editableElementsIndices.add(index);
        }
    };

    public void changeColor(int color) {
        try {
            for (int index : editableElementsIndices) {
                drawnElements.get(index).setColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStrokeWidth(float strokewidth) {
        try {
            for (int index : editableElementsIndices) {
                drawnElements.get(index).setStrokeWidth(strokewidth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeSize(float size) {
        try {
            for (int index : editableElementsIndices) {
                drawnElements.get(index).setSize(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCoordinates(float x, float y) {
        try {
            for (int index : editableElementsIndices) {
                drawnElements.get(index).setCoordinates(x, y);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeCoordinates(float x, float y) {
        try {
            for (int index : editableElementsIndices) {
                drawnElements.get(index).changeCoordinates(x, y);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public void deleteElement() {
        for (int index : editableElementsIndices) {
            drawnElements.remove(index);
        }
    }




}