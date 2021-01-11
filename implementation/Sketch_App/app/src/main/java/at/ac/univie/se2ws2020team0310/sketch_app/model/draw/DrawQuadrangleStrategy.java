package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;

public class DrawQuadrangleStrategy implements DrawStrategy {

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
        mPaint.setStrokeWidth(graphicalElement.getStrokeWidth());
        mPaint.setColor(graphicalElement.getColor());

        canvas.drawRect(graphicalElement.getXPosition(), graphicalElement.getYPosition(), graphicalElement.getXPosition() + ((Quadrangle) graphicalElement).getLength(), graphicalElement.getYPosition() + ((Quadrangle) graphicalElement).getHeight(), mPaint);
    }
}
