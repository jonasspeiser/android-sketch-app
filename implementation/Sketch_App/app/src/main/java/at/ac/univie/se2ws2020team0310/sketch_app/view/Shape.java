package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Paint;

abstract class Shape {
    //TODO Paint hier deklarieren (in Klassendiagramm "Color" zu Paint-Objekt ändern)
    private Paint mPaint;
    private float xPosition, yPosition, size;

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

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }



}
