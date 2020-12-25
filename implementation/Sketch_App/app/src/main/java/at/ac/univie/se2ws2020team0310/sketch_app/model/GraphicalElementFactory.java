package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Paint;
import at.ac.univie.se2ws2020team0310.sketch_app.BuildConfig;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTextStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Circle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Line;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Triangle;
import at.ac.univie.se2ws2020team0310.sketch_app.view.MainActivity;

public final class GraphicalElementFactory {

    private GraphicalElementFactory() {
        // empty constructor
    }

    //Implementation of FREEHAND and COMPOSITE_SHAPE until DEAD
    public static GraphicalElement createElement(EGraphicalElementType type, Paint paint) throws AppException {
        switch (type) {
            case LINE:
                return createLine(paint);
            case CIRCLE:
                return createCircle(paint);
            case FREEHAND:
            case COMPOSITE_SHAPE:
                break;
            case TRIANGLE:
                return createTriangle(paint);
            case QUADRANGLE:
                return createQuadrangle(paint);
            case TEXT_FIELD:
                return createText(paint);
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

    private static Text createText(Paint paint) {
        Text mText = new Text(new DrawTextStrategy());
        Paint mPaint = new Paint(paint);
        mPaint.setStyle(Paint.Style.FILL);
        mText.setObjectPaint(mPaint);
        return mText;
    }

    private static Triangle createTriangle(Paint paint) {
        Triangle mTriangle = new Triangle(new DrawTriangleStrategy());
        Paint mPaint = new Paint(paint);
        mTriangle.setObjectPaint(mPaint);
        mTriangle.setShapeSize(150);
        return mTriangle;
    }

    private static Line createLine(Paint paint) {
        Line mLine = new Line(new DrawLineStrategy());
        Paint mPaint = new Paint(paint);
        mLine.setObjectPaint(mPaint);
        return mLine;
    }

    private static Circle createCircle(Paint paint) {
        Circle mCircle = new Circle(new DrawCircleStrategy());
        Paint mPaint = new Paint(paint);
        mCircle.setObjectPaint(mPaint);
        mCircle.setShapeSize(150);
        mCircle.setRadius( mCircle.getShapeSize() / 2);
        return mCircle;
    }

    private static Quadrangle createQuadrangle(Paint paint) {
        Quadrangle mSquare = new Quadrangle(new DrawQuadrangleStrategy());
        Paint mPaint = new Paint(paint);
        mSquare.setObjectPaint(mPaint);
        mSquare.setShapeSize(150);
        mSquare.setLength(mSquare.getShapeSize());
        mSquare.setHeight(mSquare.getShapeSize());
        return mSquare;
    }

}
