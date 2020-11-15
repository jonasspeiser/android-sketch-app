package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Paint;

abstract class Shape {
    //TODO Abstrakte Klasse, welche die Methoden für die Unterklassen Circle, etc vorgibt
    private Paint mPaint;
    private float xPosition, yPosition, radius;

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }



}
