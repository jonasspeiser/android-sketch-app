package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public abstract class GraphicalElement {

// Attributes

    private final DrawStrategy drawStrategy;

    protected float xPosition, yPosition, size;

    protected Paint objectPaint;

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

    public Paint getObjectPaint() {
        return objectPaint;
    }

    public void setObjectPaint(Paint objectPaint) {
        this.objectPaint = objectPaint;
    }

    public void draw(Canvas canvas) {
        drawStrategy.draw(canvas, this);
    }

// Other Methods

    public void changeCoordinates(float x, float y) {
        this.setCoordinates(x, y);
    }

    public void setColor(int color) {
        getObjectPaint().setColor(color);
    }

    public void setStrokeWidth(float strokeWidth) {
        getObjectPaint().setStrokeWidth(strokeWidth);
    }

    public abstract boolean isWithinElement(float x, float y);
}
