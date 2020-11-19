package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.BuildConfig;
import at.ac.univie.se2ws2020team0310.sketch_app.model.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Circle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Line;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Quadrangle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Text;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Triangle;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawQuadrangle;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawTextStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawTriangleStrategy;

public final class GraphicalElementFactory {

    private GraphicalElementFactory() {
        // empty constructor
    }

    //Implementation of DRAWING (Free-Hand) and COMPOSITE_SHAPE until DEAD
    public static GraphicalElement createElement(EGraphicalElementType type) throws AppException {
        switch (type) {
            case LINE:
                return createLine();
            case CIRCLE:
                return createCircle();
            case DRAWING:
            case COMPOSITE_SHAPE:
                break;
            case TRIANGLE:
                return createTriangle();
            case QUADRANGLE:
                return createQuadrangle();
            case TEXT_FIELD:
                return createText();
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

    private static Text createText() {
        Text mText = new Text(new DrawTextStrategy());
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mPaint.setStyle(Paint.Style.FILL);
        mText.setObjectPaint(mPaint);
        return mText;
    }

    private static Triangle createTriangle() {
        Triangle mTriangle = new Triangle(new DrawTriangleStrategy());
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mTriangle.setObjectPaint(mPaint);
        mTriangle.setShapeSize(150);
        return mTriangle;
    }

    private static Line createLine() {
        Line mLine = new Line(new DrawLineStrategy());
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mLine.setObjectPaint(mPaint);
        return mLine;
    }

    private static Circle createCircle() {
        Circle mCircle = new Circle(new DrawCircleStrategy());
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mCircle.setObjectPaint(mPaint);
        mCircle.setShapeSize(70);
        return mCircle;
    }

    private static Quadrangle createQuadrangle() {
        Quadrangle mSquare = new Quadrangle(new DrawQuadrangle());
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mSquare.setObjectPaint(mPaint);
        mSquare.setShapeSize(150);
        mSquare.setLength(mSquare.getShapeSize());
        mSquare.setHeight(mSquare.getShapeSize());
        return mSquare;
    }

    // circle
    // line
    //etc.
}
