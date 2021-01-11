package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCombinedShapeStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawFreehandStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTextStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Circle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Line;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Triangle;

public final class GraphicalElementFactory {

    private static int ELEMENT_ID = 1;  // static counter of Elements

    private GraphicalElementFactory() {
        // empty constructor
    }

    public static GraphicalElement createElement(EGraphicalElementType type, int color, float size, float strokewidth) throws AppException {
        switch (type) {
            case LINE:
                return createLine(color, size, strokewidth);
            case CIRCLE:
                return createCircle(color, size, strokewidth);
            case FREEHAND:
                return createFreehand(color, size, strokewidth);
            case COMBINED_SHAPE:
                return createCombinedShape();
            case TRIANGLE:
                return createTriangle(color, size, strokewidth);
            case QUADRANGLE:
                return createQuadrangle(color, size, strokewidth);
            case TEXT_FIELD:
                return createText(color, size, strokewidth);
            default:
                throw new AppException("Unknown type: " + type);
        }
    }

    /**
     * Create a new GraphicalElement as a copy of the given element
     * @param element   the element to copy from
     * @return          a fresh copy of the element
     */
    public static GraphicalElement createElement(GraphicalElement element) {
        return element.copy();
    }

    //TODO: color wird nicht genutzt, strokeWidth ist für Text eig irrelevant
    private static Text createText(int color, float size, float strokewidth) {
        Text mText = new Text(new DrawTextStrategy());
        mText.setColor(Color.BLACK);
        mText.setSize(size);
        mText.setStrokeWidth(strokewidth);
        return mText;
    }

    private static Triangle createTriangle(int color, float size, float strokewidth) {
        Triangle mTriangle = new Triangle(new DrawTriangleStrategy());
        mTriangle.setColor(color);
        mTriangle.setSize(size);
        mTriangle.setStrokeWidth(strokewidth);
        return mTriangle;
    }

    //TODO: size wird nicht genutzt
    private static Line createLine(int color, float size, float strokewidth) {
        Line mLine = new Line(new DrawLineStrategy());
        mLine.setColor(color);
        mLine.setStrokeWidth(strokewidth);
        return mLine;
    }

    public static Circle createCircle(int color, float size, float strokewidth) {
        Circle mCircle = new Circle(new DrawCircleStrategy());
        mCircle.setColor(color);
        mCircle.setSize(size);
        mCircle.setStrokeWidth(strokewidth);
        return mCircle;
    }

    private static Quadrangle createQuadrangle(int color, float size, float strokewidth) {
        Quadrangle mSquare = new Quadrangle(new DrawQuadrangleStrategy());
        mSquare.setColor(color);
        mSquare.setSize(size);
        mSquare.setStrokeWidth(strokewidth);
        return mSquare;
    }

    private static Freehand createFreehand(int color, float size, float strokeWidth) {
        Freehand freehand = new Freehand(new DrawFreehandStrategy());
        Path path = new Path();
        freehand.setObjectPath(path);
        freehand.setColor(color);
        freehand.setSize(size);
        freehand.setStrokeWidth(strokeWidth);

        return freehand;
    }

    /**
     * Create an empty CombinedShape
     * Attributes will be set at a later point
     * @return  the new CombinedShape
     */
    private static CombinedShape createCombinedShape() {
        // assign a different ID to each Combined Shape
        CombinedShape combinedShape = new CombinedShape(new DrawCombinedShapeStrategy(), ELEMENT_ID);
        ELEMENT_ID++;

        return combinedShape;
    }

    public static Paint initializePaint() {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        return mPaint;
    }

}
