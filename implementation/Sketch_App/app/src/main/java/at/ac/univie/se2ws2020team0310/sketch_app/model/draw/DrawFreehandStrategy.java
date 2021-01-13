package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class DrawFreehandStrategy implements IDrawStrategy {
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

        Path path = ((Freehand) graphicalElement).getObjectPath();   // cast
        canvas.drawPath(path, mPaint); //drawing

    }
}
