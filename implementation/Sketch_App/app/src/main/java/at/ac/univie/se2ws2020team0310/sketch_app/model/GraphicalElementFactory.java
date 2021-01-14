package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.decorators.BoldDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.decorators.BoldItalicDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.decorators.ItalicDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.decorators.UnderlineDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCombinedShapeStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawFreehandStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTextStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.IDrawStrategy;
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

    private GraphicalElementFactory() {
        // empty constructor
    }

    public static GraphicalElement createElement(EGraphicalElementType type, int color, float size,
                                                 float strokeWidth) throws AppException {
        switch (type) {
            case LINE:
                return createLine(color, strokeWidth);
            case CIRCLE:
                return createCircle(color, size, strokeWidth);
            case FREEHAND:
                return createFreehand(color, size, strokeWidth);
            case COMBINED_SHAPE:
                return createCombinedShape();
            case TRIANGLE:
                return createTriangle(color, size, strokeWidth);
            case QUADRANGLE:
                return createQuadrangle(color, size, strokeWidth);
            case TEXT_FIELD:
                return createText(color, size);
            default:
                throw new AppException("Unknown type: " + type);
        }
    }

    /**
     * Create a new GraphicalElement as a copy of the given element
     *
     * @param element the element to copy from
     * @return a fresh copy of the element
     */
    public static GraphicalElement createElement(GraphicalElement element) {
        return element.copy();
    }

    private static Text createText(int color, float size) {
        Text mText = new Text(new DrawTextStrategy());
        mText.setColor(color);
        mText.setSize(size);
        return mText;
    }

    private static Triangle createTriangle(int color, float size, float strokeWidth) {
        Triangle mTriangle = new Triangle(new DrawTriangleStrategy());
        mTriangle.setColor(color);
        mTriangle.setSize(size);
        mTriangle.setStrokeWidth(strokeWidth);
        return mTriangle;
    }

    private static Line createLine(int color, float strokeWidth) {
        Line mLine = new Line(new DrawLineStrategy());
        mLine.setColor(color);
        mLine.setStrokeWidth(strokeWidth);
        return mLine;
    }

    public static Circle createCircle(int color, float size, float strokeWidth) {
        Circle mCircle = new Circle(new DrawCircleStrategy());
        mCircle.setColor(color);
        mCircle.setSize(size);
        mCircle.setStrokeWidth(strokeWidth);
        return mCircle;
    }

    private static Quadrangle createQuadrangle(int color, float size, float strokeWidth) {
        Quadrangle mSquare = new Quadrangle(new DrawQuadrangleStrategy());
        mSquare.setColor(color);
        mSquare.setSize(size);
        mSquare.setStrokeWidth(strokeWidth);
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
     * Create an empty CombinedShape Attributes will be set at a later point
     *
     * @return the new CombinedShape
     */
    private static CombinedShape createCombinedShape() {
        return new CombinedShape(new DrawCombinedShapeStrategy());
    }

    public static Text createBoldText(GraphicalElement graphicalElement) {
        IDrawStrategy oldStrategy = graphicalElement.getDrawStrategy();
        IDrawStrategy newStrategy;

        if (oldStrategy instanceof ItalicDecorator) {
            newStrategy = new BoldItalicDecorator(oldStrategy);
        } else {
            newStrategy = new BoldDecorator(oldStrategy);
        }

        Text mText = new Text(newStrategy);
        mText.setColor(graphicalElement.getColor());
        mText.setSize(graphicalElement.getSize());
        return mText;
    }

    public static Text createItalicText(GraphicalElement graphicalElement) {
        IDrawStrategy oldStrategy = graphicalElement.getDrawStrategy();
        IDrawStrategy newStrategy;

        if (oldStrategy instanceof BoldDecorator) {
            newStrategy = new BoldItalicDecorator(oldStrategy);
        } else {
            newStrategy = new ItalicDecorator(oldStrategy);
        }

        Text mText = new Text(newStrategy);
        mText.setColor(graphicalElement.getColor());
        mText.setSize(graphicalElement.getSize());
        return mText;
    }

    public static Text createUnderlineText(GraphicalElement graphicalElement) {
        IDrawStrategy oldStrategy = graphicalElement.getDrawStrategy();
        IDrawStrategy newStrategy = new UnderlineDecorator(oldStrategy);

        Text mText = new Text(newStrategy);
        mText.setColor(graphicalElement.getColor());
        mText.setSize(graphicalElement.getSize());
        return mText;
    }

}
