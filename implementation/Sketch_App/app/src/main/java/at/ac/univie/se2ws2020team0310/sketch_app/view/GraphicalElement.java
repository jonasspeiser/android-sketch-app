package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Paint;

abstract class GraphicalElement {

// Attributes

    //TODO: Klassendiagramm "Color" zu Paint-Objekt ändern
    private static Paint selectedPaint;
    private static int selectedStrokeWidth = 100;
    private static int selectedTextSize = 50;

    private float xPosition, yPosition, shapeSize;

    private Paint objectPaint;
    private int objectStrokeWidth;
    private int objectTextSize;

// Methods

    public static Paint getSelectedPaint() {
        return selectedPaint;
    }

    public static void setSelectedPaint(Paint selectedPaint) {
        GraphicalElement.selectedPaint = selectedPaint;
    }

    public static int getSelectedTextSize() {
        return selectedTextSize;
    }

    public static void setSelectedTextSize(int selectedTextSize) {
        GraphicalElement.selectedTextSize = selectedTextSize;
    }

    public static int getSelectedStrokeWidth(){
        return selectedStrokeWidth;
    }

    public static void setSelectedStrokeWidth(int selectedStrokeWidth) {
        GraphicalElement.selectedStrokeWidth = selectedStrokeWidth;
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

    public int getObjectStrokeWidth() {
        return objectStrokeWidth;
    }

    public void setObjectStrokeWidth(int objectStrokeWidth) {
        this.objectStrokeWidth = objectStrokeWidth;
    }

    public int getObjectTextSize() {
        return objectTextSize;
    }

    public void setObjectTextSize(int objectTextSize) {
        this.objectTextSize = objectTextSize;
    }



    public static void changeStrokeWidth(float strokeWidth) {
        selectedPaint.setStrokeWidth(strokeWidth);
    }



}
