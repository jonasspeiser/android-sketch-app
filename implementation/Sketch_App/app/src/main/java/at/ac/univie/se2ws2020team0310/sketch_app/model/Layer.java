package at.ac.univie.se2ws2020team0310.sketch_app.model;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class Layer {

// Attributes

    private final List<GraphicalElement> drawnElements;
    private final List<Integer> editableElementsIndices; // TODO: Mit Iterator implementieren
    private int movableElementIndex;

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
        makeEditable(selectedGraphicalElement);
        makeMovable(selectedGraphicalElement);
    }

    public void clear() {
        drawnElements.clear();
        resetEditableElements();
    }



    /**
     * Checks, whether the provided coordinates are within a graphical element and if so, makes that element editable.
     *
     * @param x coordinate on the x-axes
     * @param y coordinate on the y-axes
     * @return returns true if the provided coordinates are within an already drawn element
     */
    public boolean makeElementOnPositionEditable(float x, float y) {
        for (GraphicalElement graphicalElement : getDrawnElements()) {
            if (graphicalElement.isWithinElement(x, y)) {
                makeEditable(graphicalElement);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks, whether the provided coordinates are within a graphical element and if so, makes that element movable.
     *
     * @param x coordinate on the x-axes
     * @param y coordinate on the y-axes
     * @return returns true if the provided coordinates are within an already drawn element
     */
    public boolean makeElementOnPositionMovable(float x, float y) {
        for (GraphicalElement graphicalElement : getDrawnElements()) {
            if (graphicalElement.isWithinElement(x, y)) {
                makeMovable(graphicalElement);
                return true;
            }
        }
        return false;
    }

    public void makeMovable(GraphicalElement graphicalElement) {
        if (drawnElements.contains(graphicalElement)) {
            int index = drawnElements.indexOf(graphicalElement);
            this.movableElementIndex = index;
        }
    }

    public void makeEditable(GraphicalElement graphicalElement) { // TODO: Mit Iterator implementieren
        // TODO: Hier muss statt des if noch ein try-catch Block mit eigener Exception her ("Element not found")
        if (drawnElements.contains(graphicalElement)) {
            int index = drawnElements.indexOf(graphicalElement);
            editableElementsIndices.add(index);
        }
    }

    public void setCoordinates(float x, float y) {
        try {
            int index = this.movableElementIndex;
            drawnElements.get(index).setCoordinates(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeCoordinates(float x, float y) {
        try {
            int index = this.movableElementIndex;
            drawnElements.get(index).changeCoordinates(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeColor(int color) {        // TODO: Mit Iterator implementieren
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


    public void deleteElement() {
        for (int index : editableElementsIndices) {
            drawnElements.remove(index);
            resetEditableElements();
        }
    }

    public void resetEditableElements() {
        editableElementsIndices.clear();
    }

}