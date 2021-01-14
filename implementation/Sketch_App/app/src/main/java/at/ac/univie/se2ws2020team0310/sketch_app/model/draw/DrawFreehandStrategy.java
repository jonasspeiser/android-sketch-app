package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class DrawFreehandStrategy implements DrawStrategy {
    @Override
    public Paint initializePaint(GraphicalElement graphicalElement) {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(graphicalElement.getStrokeWidth());
        mPaint.setColor(graphicalElement.getColor());
        return mPaint;
    }

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        Paint mPaint = initializePaint(graphicalElement);

        Path path = ((Freehand) graphicalElement).getObjectPath();   // cast
        canvas.drawPath(path, mPaint); //drawing

    }
}
