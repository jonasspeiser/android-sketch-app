package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;


import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public abstract class GraphicalElement {

// Attributes

    private final DrawStrategy drawStrategy;

    protected float xPosition, yPosition, size;

    protected int color;
    protected float strokewidth;

// Constructor

    public GraphicalElement(DrawStrategy drawStrategy) { //Konstruktor
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


    //method for freehand
    public Path getPath() {
        return null;
    }

    // check if this element is a Freehand drawing
    public boolean isFreehand() {
        return false;
    }

    // change coordinates and update Path, if applicable
    // TODO: Sollte das nicht eher in die Klasse Freehand?
    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        this.setCoordinates(x, y);
        if (getPath() != null && lastTouchX > 0 && lastTouchY > 0) {
            getPath().offset(x - lastTouchX, y - lastTouchY);
        }
    }

    public DrawStrategy getDrawStrategy() {
        return this.drawStrategy;
    }

    public abstract boolean isWithinElement(float x, float y);
    protected abstract String getName();

    // toString() used for logging and identifying GraphicalElements
    @Override
    public String toString() {
        return getName() + " (" + this.xPosition + ", " + this.yPosition + ")";
    }
}
