package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.BuildConfig;
import at.ac.univie.se2ws2020team0310.sketch_app.model.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.AppException;

public final class GraphicalElementFactory {

    private GraphicalElementFactory() {
        // empty constructor
    }

    public static GraphicalElement createElement(EGraphicalElementType type, Canvas canvas) throws AppException {
        switch (type) {
            case LINE:
                break;
            case CIRCLE:
                //return createCircle(canvas);
            case DRAWING:
                break;
            case TRIANGLE:
                break;
            case QUADRANGLE:
                return createQuadrangle(canvas);
            case TEXT_FIELD:
                break;
            case COMPOSITE_SHAPE:
                break;
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

    private static Quadrangle createQuadrangle(Canvas canvas) {
        Quadrangle mSquare = new Quadrangle();
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mSquare.setObjectPaint(mPaint);
        mSquare.setShapeSize(150);
        mSquare.setLength(mSquare.getShapeSize());
        mSquare.setHeight(mSquare.getShapeSize());
        mSquare.setxPosition(mSquare.generateRandomX(canvas));
        mSquare.setyPosition(mSquare.generateRandomY(canvas));
        return mSquare;
    }

    // circle
    // line
    //etc.
}
