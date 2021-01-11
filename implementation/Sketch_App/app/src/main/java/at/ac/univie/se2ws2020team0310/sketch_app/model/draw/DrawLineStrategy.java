package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Line;

public class DrawLineStrategy implements DrawStrategy {
    @Override
    public Paint initializePaint() {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        return mPaint;
    }

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        Paint mPaint = initializePaint();
        mPaint.setStrokeWidth(graphicalElement.getStrokewidth());
        mPaint.setColor(graphicalElement.getColor());

        canvas.drawLine(((Line) graphicalElement).getStartX(), ((Line) graphicalElement).getStartY(), graphicalElement.getXPosition(), graphicalElement.getYPosition(), mPaint);
    }
}
