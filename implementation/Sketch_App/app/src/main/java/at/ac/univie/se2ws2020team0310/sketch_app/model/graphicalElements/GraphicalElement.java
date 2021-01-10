package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public abstract class GraphicalElement {

// Attributes

    private final DrawStrategy drawStrategy;

    protected float xPosition, yPosition, size;

    protected Paint objectPaint;

// Constructor

    protected GraphicalElement(DrawStrategy drawStrategy) { //Konstruktor
        this.drawStrategy = drawStrategy;
    }

// Getters and Setters

    public void setCoordinates(float x, float y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Paint getObjectPaint() {
        return objectPaint;
    }

    public void setObjectPaint(Paint objectPaint) {
        this.objectPaint = objectPaint;
    }

    public void draw(Canvas canvas) {
        drawStrategy.draw(canvas, this);
    }

    /**
     * Method for retrieving the current Path of a Freehand drawing
     * @return  the current Freehand Path or null, if another Element was selected
     */
    public Path getPath() {
        return null;
    }

    /**
     * Check if this element is a Freehand drawing
     * @return  true, only if it is a Freehand drawing
     */
    public boolean isFreehand() {
        return false;
    }

// Other Methods

    /**
     * Update current coordinates
     * @param x the new coordinate x
     * @param y the new coordinate y
     * @param lastTouchX    the previous coordinate x (used only for Freehand drawing)
     * @param lastTouchY    the previous coordinate y (used only for Freehand drawing)
     */
    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        this.setCoordinates(x, y);
    }

    public void setColor(int color) {
        getObjectPaint().setColor(color);
    }

    public void setStrokeWidth(float strokeWidth) {
        getObjectPaint().setStrokeWidth(strokeWidth);
    }

    public DrawStrategy getDrawStrategy() {
        return this.drawStrategy;
    }

    /**
     * Check if the given coordinates are within the current GraphicalElement
     * @param x coordinate x
     * @param y coordinate y
     * @return  true, if the GraphicalElement contains this position
     */
    public abstract boolean isWithinElement(float x, float y);

    /**
     * Get the name of the GraphicalElement
     * @return  the name
     */
    public abstract String getName();

    /**
     * Method used for logging and identifying GraphicalElements
     * @return a String representation of the current GraphicalElement
     */
    @Override
    public String toString() {
        return getName() + " (" + this.xPosition + ", " + this.yPosition + ")";
    }
}
