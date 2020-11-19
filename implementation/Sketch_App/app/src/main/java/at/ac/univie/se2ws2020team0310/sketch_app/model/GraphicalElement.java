package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public abstract class GraphicalElement {

// Attributes

    //TODO: Klassendiagramm "Color" zu Paint-Objekt ändern
    private static Paint selectedPaint;
    private final IDrawStrategy drawStrategy;

    protected float xPosition, yPosition, shapeSize;

    protected Paint objectPaint;

    public GraphicalElement(IDrawStrategy drawStrategy) { //Konstruktor
        this.drawStrategy = drawStrategy;
    }

// Getters and Setters

    public static Paint getSelectedPaint() {
        return selectedPaint;
    }

    public static void setSelectedPaint(Paint selectedPaint) {
        GraphicalElement.selectedPaint = selectedPaint;
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

    public float getShapeSize() {
        return shapeSize;
    }

    public void setShapeSize(float shapeSize) {
        this.shapeSize = shapeSize;
    }

    public Paint getObjectPaint() {
        return objectPaint;
    }

    public void setObjectPaint(Paint objectPaint) {
        this.objectPaint = objectPaint;
    }

    public static void changeTextSize(float textSize) {
        selectedPaint.setTextSize(textSize);
    }

    public void draw(Canvas canvas) {
        drawStrategy.draw(canvas, this);
    }
}
