package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Circle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class DrawCircleStrategy implements IDrawStrategy {

    @Override
    public Paint initializePaint(){
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        return mPaint;
    }

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        Paint mPaint = initializePaint();
        mPaint.setStrokeWidth(graphicalElement.getStrokeWidth());
        mPaint.setColor(graphicalElement.getColor());
        canvas.drawCircle(graphicalElement.getXPosition(), graphicalElement.getYPosition(),((Circle) graphicalElement).getRadius(), mPaint);
    }
}
