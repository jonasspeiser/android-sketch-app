package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Canvas;

import java.util.Random;

public class Circle extends Shape {


    public float generateRandomX(Canvas canvas) {
        int minX = (int) this.getSize() * 2;
        int maxX = canvas.getWidth() - ( (int) this.getSize() *2 );
        Random random = new Random();
        float mPivotX = random.nextInt(maxX - minX + 1) + minX;
        return mPivotX;

    }

    public float generateRandomY(Canvas canvas) {
        int minY = (int) this.getSize() * 2;
        int maxY = canvas.getHeight() - ( (int) this.getSize() *2 );
        Random random = new Random();
        float mPivotY = random.nextInt(maxY - minY + 1) + minY;
        return mPivotY;

    }


}
