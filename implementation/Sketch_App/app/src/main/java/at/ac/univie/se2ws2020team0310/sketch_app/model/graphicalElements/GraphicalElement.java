package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public abstract class GraphicalElement implements Cloneable {


// Attributes

    private final DrawStrategy drawStrategy;

    protected float xPosition, yPosition, size;

    protected int color;
    protected float strokewidth;

// Constructor

    protected GraphicalElement(DrawStrategy drawStrategy) { //Konstruktor
        this.drawStrategy = drawStrategy;
    }

    /**
     * Copy Constructor for GraphicalElements
     * @param copy  the element to copy from
     */
    protected GraphicalElement(GraphicalElement copy) {
        this.drawStrategy = copy.drawStrategy;
        setXPosition(copy.xPosition);
        setYPosition(copy.yPosition);
        if (copy.objectPaint != null) {
            setObjectPaint(new Paint(copy.objectPaint));
            setColor(copy.objectPaint.getColor());
            setStrokeWidth(copy.objectPaint.getStrokeWidth());
        }
        setSize(copy.size);
    }

// Getters and Setters

    /**
     *  Set the coordinates of the new GraphicalElement
     * @param x coordinate x
     * @param y coordinate y
     * @throws AppException Overriding classes may throw this exception
     */
    public void setCoordinates(float x, float y) throws AppException {
        this.xPosition = x;
        this.yPosition = y;
    }

    public float getXPosition() {
        return xPosition;
    }

    public void setXPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getYPosition() {
        return yPosition;
    }

    public void setYPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getStrokewidth() {
        return strokewidth;
    }

    public void setStrokewidth(float strokewidth) {
        this.strokewidth = strokewidth;
    }


// Other Methods

    public void draw(Canvas canvas) {
        drawStrategy.draw(canvas, this);
    }

    /**
     * Method for retrieving the current Path of a Freehand drawing
     *
     * @return the current Freehand Path or null, if another Element was selected
     */
    public Path getPath() {
        return null;
    }

    /**
     * Check if this element is a Freehand drawing
     *
     * @return true, only if it is a Freehand drawing
     */
    public boolean isFreehand() {
        return false;
    }

// Other Methods

    /**
     * Update current coordinates
     *
     * @param x          the new coordinate x
     * @param y          the new coordinate y
     * @param lastTouchX the previous coordinate x (used only for Freehand drawing)
     * @param lastTouchY the previous coordinate y (used only for Freehand drawing)
     */
    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) throws AppException {
        this.setCoordinates(x, y);
    }

    public DrawStrategy getDrawStrategy() {
        return this.drawStrategy;
    }

    /**
     * Check if the given coordinates are within the current GraphicalElement
     *
     * @param x coordinate x
     * @param y coordinate y
     * @return true, if the GraphicalElement contains this position
     */
    public abstract boolean isWithinElement(float x, float y);

    /**
     * Get the name of the GraphicalElement
     *
     * @return the name
     */
    public abstract String getName();

    /**
     * Method used for logging and identifying GraphicalElements
     *
     * @return a String representation of the current GraphicalElement
     */
    @Override
    public String toString() {
        return getName() + " (" + this.xPosition + ", " + this.yPosition + ")";
    }

    /**
     * Method used for creating a deep copy of the current GraphicalElement
     * @return  a deep copy of the element
     */
    public abstract GraphicalElement copy();
}
