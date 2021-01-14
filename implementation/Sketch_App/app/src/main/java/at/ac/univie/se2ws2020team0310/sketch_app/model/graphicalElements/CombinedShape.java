package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.IDrawStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Composite Pattern to create a new Shape consisting of a List of Graphical
 * Elements
 */
public class CombinedShape extends GraphicalElement {

    private final List<GraphicalElement> elements;
    private String name;
    private GraphicalElement pivotElement;  // the pivot element, upon which the positioning and movement of the whole Combined Shape depends

    public CombinedShape(IDrawStrategy drawStrategy) {
        this(drawStrategy, "CombiShape");
    }

    public CombinedShape(IDrawStrategy drawStrategy, String name) {
        super(drawStrategy);
        this.elements = new ArrayList<>();
        this.name = name;
        this.pivotElement = null;
    }

    /**
     * Copy Constructor for creating a new CombinedShape from an existing one
     *
     * @param copy the CombinedShape to copy from
     */
    public CombinedShape(CombinedShape copy) {
        super(copy);
        // create a new List of elements with copies of each element in the original Combined Shape
        this.elements = new ArrayList<>(copy.elements.size());
        for (GraphicalElement copyElement : copy.elements) {
            this.elements.add(copyElement.copy());
        }
        this.name = copy.name;
        this.pivotElement = null;
    }

    /**
     * Check if the given coordinates match one of the elements within this CombinedShape
     *
     * @param x coordinate x
     * @param y coordinate y
     * @return true, if the coordinates are within one of the elements
     */
    @Override
    public boolean isWithinElement(float x, float y) {
        for (GraphicalElement element : this.elements) {
            if (element.isWithinElement(x, y)) {
                // the pivot element will be the one the user has touched first for moving
                this.pivotElement = element;
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add a new GraphicalElement to this CombinedShape
     *
     * @param element the element to add, if not selected already
     * @throws AppException in case the element is already present in the list
     */
    public void add(GraphicalElement element) throws AppException {
        if (this.elements.contains(element)) {
            throw new AppException(element + " is already selected");
        }
        this.elements.add(element);
    }

    public List<GraphicalElement> getElements() {
        return this.elements;
    }

    @Override
    public String toString() {
        return "Combined Shape: " + this.getName();
    }

    @Override
    public GraphicalElement copy() {
        return new CombinedShape(this);
    }

    public int getElementsCount() {
        return this.elements.size();
    }

    /**
     * Update the coordinates of the Combined Shape The same offset has to be applied to all
     * elements included in this Combined Shape, in order to display them correctly
     *
     * @param x coordinate x where the first element of the Combined Shape will be set
     * @param y coordinate y where the first element of the Combined Shape will be set
     * @throws AppException in case there are no elements to display
     */
    @Override
    public void setCoordinates(float x, float y) throws AppException {
        if (this.elements.isEmpty()) {
            throw new AppException("Cannot set coordinates for an empty Combined Shape");
        }
        // calculate the offset between the current coordinates and the coordinates of the first element
        // the first element will be either the pivot or just the first element in the list
        GraphicalElement firstElement =
                this.pivotElement != null ? this.pivotElement : this.elements.get(0);
        float offsetX = x - firstElement.getXPosition();
        float offsetY = y - firstElement.getYPosition();
        for (GraphicalElement element : this.elements) {
            element.changeCoordinates(element.getXPosition() + offsetX,
                    element.getYPosition() + offsetY, element.getXPosition(),
                    element.getYPosition());
        }
    }

    /**
     * The Combined Shape will use the x coordinate of the pivot or first element in the list, if
     * applicable
     *
     * @return the x coordinate
     */
    @Override
    public float getXPosition() {
        if (this.elements.isEmpty()) {
            return this.xPosition;
        }
        return this.pivotElement != null ? this.pivotElement.getXPosition()
                : this.elements.get(0).getXPosition();
    }

    /**
     * The Combined Shape will use the y coordinate of the pivot or first element in the list, if
     * applicable
     *
     * @return the y coordinate
     */
    @Override
    public float getYPosition() {
        if (this.elements.isEmpty()) {
            return this.yPosition;
        }
        return this.pivotElement != null ? this.pivotElement.getYPosition()
                : this.elements.get(0).getYPosition();
    }

    /**
     * Set the same color to each element
     *
     * @param color the color to set
     */
    @Override
    public void setColor(int color) {
        if (this.elements != null) {
            for (GraphicalElement element : this.elements) {
                element.setColor(color);
            }
        }
    }

    /**
     * Set the same stroke width to each element
     *
     * @param strokeWidth the stroke width to set
     */
    @Override
    public void setStrokeWidth(float strokeWidth) {
        if (this.elements != null) {
            for (GraphicalElement element : this.elements) {
                element.setStrokeWidth(strokeWidth);
            }
        }
    }

    /**
     * set the same size to each element
     *
     * @param size size to set
     */
    @Override
    public void setSize(float size) {
        if (this.elements != null) {
            for (GraphicalElement element : this.elements) {
                element.setSize(size);
            }
        }
    }
}
