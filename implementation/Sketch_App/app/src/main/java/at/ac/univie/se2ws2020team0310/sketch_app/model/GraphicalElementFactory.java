package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.BuildConfig;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawFreehandStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTextStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Circle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Line;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Triangle;

public final class GraphicalElementFactory {

    private GraphicalElementFactory() {
        // empty constructor
    }

    //Implementation of FREEHAND and COMPOSITE_SHAPE until DEAD
    public static GraphicalElement createElement(EGraphicalElementType type, int color, float size, float strokewidth) throws AppException {
        switch (type) {
            case LINE:
                return createLine(color, size, strokewidth);
            case CIRCLE:
                return createCircle(color, size, strokewidth);
            case FREEHAND:
                return createFreehand(color, size, strokewidth);
            case COMPOSITE_SHAPE:
                break;
            case TRIANGLE:
                return createTriangle(color, size, strokewidth);
            case QUADRANGLE:
                return createQuadrangle(color, size, strokewidth);
            case TEXT_FIELD:
                return createText(color, size, strokewidth);
            default:
                throw new AppException("Unknown type: " + type);
        }

        // Defensive Programming
        // assertions do not work in Android
        // use BuildConfig.DEBUG parameter
        if (BuildConfig.DEBUG) {
            throw new AssertionError("We should never get here");
        }
        return null;
    }

    private static Text createText(int color, float size, float strokewidth) {
        Text mText = new Text(new DrawTextStrategy());
        Paint mPaint = new Paint(initializePaint());
        mPaint.setStyle(Paint.Style.FILL);
        mText.setObjectPaint(mPaint);
        mText.setColor(Color.BLACK);
        mText.setSize(size);
        mText.setStrokeWidth(strokewidth);
        return mText;
    }

    private static Triangle createTriangle(int color, float size, float strokewidth) {
        Triangle mTriangle = new Triangle(new DrawTriangleStrategy());
        Paint mPaint = new Paint(initializePaint());
        mTriangle.setObjectPaint(mPaint);
        mTriangle.setColor(color);
        mTriangle.setSize(size);
        mTriangle.setStrokeWidth(strokewidth);
        return mTriangle;
    }

    private static Line createLine(int color, float size, float strokewidth) {
        Line mLine = new Line(new DrawLineStrategy());
        Paint mPaint = new Paint(initializePaint());
        mLine.setObjectPaint(mPaint);
        mLine.setColor(color);
        mLine.setStrokeWidth(strokewidth);
        return mLine;
    }

    public static Circle createCircle(int color, float size, float strokewidth) {
        Circle mCircle = new Circle(new DrawCircleStrategy());
        Paint mPaint = new Paint(initializePaint());
        mCircle.setObjectPaint(mPaint);
        mCircle.setColor(color);
        mCircle.setSize(size);
        mCircle.setStrokeWidth(strokewidth);
        return mCircle;
    }

    private static Quadrangle createQuadrangle(int color, float size, float strokewidth) {
        Quadrangle mSquare = new Quadrangle(new DrawQuadrangleStrategy());
        Paint mPaint = new Paint(initializePaint());
        mSquare.setObjectPaint(mPaint);
        mSquare.setColor(color);
        mSquare.setSize(size);
        mSquare.setStrokeWidth(strokewidth);
        return mSquare;
    }

    private static Freehand  createFreehand(int color, float size, float strokewidth) { // TODo
        Freehand freehand = new Freehand(new DrawFreehandStrategy());
        Paint mPaint = new Paint(initializePaint());
        Path path = new Path();
        freehand.setObjectPath(path);
        freehand.setObjectPaint(mPaint);
        freehand.setColor(color);
        freehand.setSize(size);
        freehand.setStrokeWidth(strokewidth);

        return freehand;
    }

    public static Paint initializePaint() {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        return mPaint;
    }

}
