package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Circle extends Shape {

    public Paint mCirclePaint;
    private float mCircleX, mCircleY, mCircleRadius;

    public Paint getmCirclePaint() {
        return mCirclePaint;
    }

    public void setmCirclePaint(Paint mCirclePaint) {
        this.mCirclePaint = mCirclePaint;
    }

    public float getmCircleX() {
        return mCircleX;
    }

    public void setmCircleX(float mCircleX) {
        this.mCircleX = mCircleX;
    }

    public float getmCircleY() {
        return mCircleY;
    }

    public void setmCircleY(float mCircleY) {
        this.mCircleY = mCircleY;
    }

    public float getmCircleRadius() {
        return mCircleRadius;
    }

    public void setmCircleRadius(float mCircleRadius) {
        this.mCircleRadius = mCircleRadius;
    }

    public float generateRandomWidth(Canvas canvas) {
        int minX = (int) this.getmCircleRadius() * 2;
        int maxX = canvas.getWidth() - ( (int) this.getmCircleRadius() *2 );
        Random random = new Random();
        float mPivotX = random.nextInt(maxX - minX + 1) + minX;
        return mPivotX;

    }

    public float generateRandomHeight(Canvas canvas) {
        int minY = (int) this.getmCircleRadius() * 2;
        int maxY = canvas.getHeight() - ( (int) this.getmCircleRadius() *2 );
        Random random = new Random();
        float mPivotY = random.nextInt(maxY - minY + 1) + minY;
        return mPivotY;

    }


}
