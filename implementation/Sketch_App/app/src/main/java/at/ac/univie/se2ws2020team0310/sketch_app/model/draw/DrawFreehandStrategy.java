package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class DrawFreehandStrategy implements IDrawStrategy {

    public Path initializePath(GraphicalElement graphicalElement) {
        Path mPath = new Path();

        mPath = ((Freehand) graphicalElement).getObjectPath();   // cast

        return mPath;
    }

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
        Path mPath = initializePath(graphicalElement);

        canvas.drawPath(mPath, mPaint); //drawing

    }
}
