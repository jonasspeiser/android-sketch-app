package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Circle extends Shape {


    public float generateRandomWidth(Canvas canvas) {
        int minX = (int) this.getRadius() * 2;
        int maxX = canvas.getWidth() - ( (int) this.getRadius() *2 );
        Random random = new Random();
        float mPivotX = random.nextInt(maxX - minX + 1) + minX;
        return mPivotX;

    }

    public float generateRandomHeight(Canvas canvas) {
        int minY = (int) this.getRadius() * 2;
        int maxY = canvas.getHeight() - ( (int) this.getRadius() *2 );
        Random random = new Random();
        float mPivotY = random.nextInt(maxY - minY + 1) + minY;
        return mPivotY;

    }


}
