package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.util.Log;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.ElementNotFoundException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.ElementCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.IndexCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.IterableCollection;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.Iterator;

public class Layer {

    private static final String LAYER_TAG = "Layer";

// Attributes

    private final IterableCollection drawnElements;
    private final IterableCollection editableElementsIndices;
    private int movableElementIndex;

    private boolean visible;

// Constructors

    public Layer() {
        this.drawnElements = new ElementCollection();
        this.editableElementsIndices = new IndexCollection();
        this.visible = true;
    }

// Getters and Setters

    public boolean isEmpty() {
        return (drawnElements.size() <= 0);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

// Other Methods

    public void storeElement(GraphicalElement selectedGraphicalElement) {
        // check if element is already present, then add it
        if (selectedGraphicalElement != null && !drawnElements.contains(selectedGraphicalElement)) {
            drawnElements.add(selectedGraphicalElement);
            makeEditable(selectedGraphicalElement);
            makeMovable(selectedGraphicalElement);
        }
    }

    public void clear() {
        drawnElements.clear();
        resetEditableElements();
    }


    /**
     * Checks, whether the provided coordinates are within a graphical element and if so, makes that
     * element editable.
     *
     * @param x coordinate on the x-axes
     * @param y coordinate on the y-axes
     * @return returns true if the provided coordinates are within an already drawn element
     */
    public boolean makeElementOnPositionEditable(float x, float y) {
        Iterator elementsIterator = drawnElements.createIterator();
        while (elementsIterator.hasMore()) {
            GraphicalElement graphicalElement = (GraphicalElement) elementsIterator.getNext();
            if (graphicalElement.isWithinElement(x, y)) {
                makeEditable(graphicalElement);
                Log.i(LAYER_TAG, "Selected editable element: " + graphicalElement);
                return true;
            }
        }
        Log.d(LAYER_TAG, "No element to edit was selected");
        return false;
    }


    /**
     * Checks, whether the provided coordinates are within a graphical element and if so, makes that
     * element movable.
     *
     * @param x coordinate on the x-axes
     * @param y coordinate on the y-axes
     * @return returns true if the provided coordinates are within an already drawn element
     */
    public boolean makeElementOnPositionMovable(float x, float y) {
        Iterator elementsIterator = drawnElements.createIterator();
        while (elementsIterator.hasMore()) {
            GraphicalElement graphicalElement = (GraphicalElement) elementsIterator.getNext();
            if (graphicalElement.isWithinElement(x, y)) {
                makeMovable(graphicalElement);
                Log.i(LAYER_TAG, "Selected movable element: " + graphicalElement);
                return true;
            }
        }
        Log.d(LAYER_TAG, "No element to move was selected");
        return false;
    }

    public void makeMovable(GraphicalElement graphicalElement) {
        try {
            int index = drawnElements.indexOf(graphicalElement);
            this.movableElementIndex = index;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeEditable(GraphicalElement graphicalElement) {
        try {
            int index = drawnElements.indexOf(graphicalElement);
            editableElementsIndices.add(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCoordinates(float x, float y) {
        try {
            int index = this.movableElementIndex;
            GraphicalElement currentElement = (GraphicalElement) drawnElements.get(index);
            currentElement.setCoordinates(x, y);
        } catch (Exception e) {
            Log.e(LAYER_TAG, e.getLocalizedMessage());
        }
    }

    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        try {
            int index = this.movableElementIndex;
            GraphicalElement currentElement = (GraphicalElement) drawnElements.get(index);
            currentElement.changeCoordinates(x, y, lastTouchX, lastTouchY);
        } catch (Exception e) {
            e.printStackTrace();    // TODO replace printStackTrace with logging or continue throwing the Exception to be handled at a higher level
        }
    }

    public void changeColor(int color) {
        try {
            Iterator indexIterator = editableElementsIndices.createIterator();
            while (indexIterator.hasMore()) {
                int index = (int) indexIterator.getNext();
                GraphicalElement currentElement = (GraphicalElement) drawnElements.get(index);
                currentElement.setColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeStrokeWidth(float strokewidth) {
        try {
            Iterator indexIterator = editableElementsIndices.createIterator();
            while (indexIterator.hasMore()) {
                int index = (int) indexIterator.getNext();
                GraphicalElement currentElement = (GraphicalElement) drawnElements.get(index);
                currentElement.setStrokeWidth(strokewidth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeSize(float size) {
        try {
            Iterator indexIterator = editableElementsIndices.createIterator();
            while (indexIterator.hasMore()) {
                int index = (int) indexIterator.getNext();
                GraphicalElement currentElement = (GraphicalElement) drawnElements.get(index);
                currentElement.setSize(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // TODO rename this method to match the implemented behavior, like deleteEditableElements
    public void deleteElement() {
        Iterator indexIterator = editableElementsIndices.createIterator();
        while (indexIterator.hasMore()) {
            int index = (int) indexIterator.getNext();
            drawnElements.remove(index);
            resetEditableElements();
        }
    }

    public void resetEditableElements() {
        editableElementsIndices.clear();
    }

    public Iterator createIterator() {
        return drawnElements.createIterator();
    }

    /**
     * Check if there is a GraphicalElement at the position (x, y) and if true, add to the current
     * Combined Shape
     *
     * @param x                    the coordinate x
     * @param y                    the coordinate y
     * @param currentCombinedShape the curent Combined Shape
     * @return true, if an Element was found at position (x, y) and added to the Combined Shape
     */
    public boolean addElementToCombinedShape(float x, float y, CombinedShape currentCombinedShape)
            throws AppException {
        Iterator elementsIterator = drawnElements.createIterator();
        while (elementsIterator.hasMore()) {
            GraphicalElement graphicalElement = (GraphicalElement) elementsIterator.getNext();
            if (graphicalElement.isWithinElement(x, y)) {
                // add element to current Combined Shape
                currentCombinedShape.add(graphicalElement);
                Log.i(LAYER_TAG, "Selected Element for Combined Shape: " + graphicalElement);
                return true;
            }
        }
        Log.d(LAYER_TAG, "No element to add to the Combined Shape was selected");
        return false;
    }

    /**
     * Check if the current Layer contains the given GraphicalElement
     *
     * @param graphicalElement the element to check
     * @return true, if the Layer contains the element
     */
    public boolean containsElement(GraphicalElement graphicalElement) {
        return drawnElements.contains(graphicalElement);
    }

    /**
     * Remove the Element from the current Layer
     *
     * @param graphicalElement the element to remove
     */
    public void removeElement(GraphicalElement graphicalElement) {
        drawnElements.remove(graphicalElement);
    }
}
