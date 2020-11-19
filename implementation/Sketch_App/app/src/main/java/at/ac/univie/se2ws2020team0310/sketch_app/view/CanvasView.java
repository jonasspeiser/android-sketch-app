package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.viewmodel.GraphicalElementFactory;

public class CanvasView extends View {

// Attributes
    // TODO initiate in Constructor and only adapt on size change
    private Bitmap mBitmap;
    private Canvas mCanvas;

    private GraphicalElement selectedGraphicalElement;
    private final List <GraphicalElement> drawnElements = new ArrayList<>();

// Constructors
    public CanvasView(Context context) {
        super(context);

        init(null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

// Getters and Setters

    public GraphicalElement getSelectedGraphicalElement() {
        return selectedGraphicalElement;
    }

// Methods

    void init(@Nullable AttributeSet set) {
        //TODO: Paint Objekt wirklich hier initiieren??

        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        GraphicalElement.setSelectedPaint(mPaint);
    }

    @Override
    protected void onSizeChanged(int width, int height, int old_width, int old_height) {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    public GraphicalElement getLastElement() {
        return drawnElements.get(drawnElements.size() - 1);
    }

    public void selectLine() {

        try {
            selectedGraphicalElement = GraphicalElementFactory.createElement(EGraphicalElementType.LINE);
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectCircle() {

        try {
            selectedGraphicalElement = GraphicalElementFactory.createElement(EGraphicalElementType.CIRCLE);
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public void selectQuadrangle() {

        // use a Factory to create the Quadrangle as a GraphicalElement
        try {
            selectedGraphicalElement = GraphicalElementFactory.createElement(EGraphicalElementType.QUADRANGLE);
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public void selectTriangle() {

        try {
            selectedGraphicalElement = GraphicalElementFactory.createElement(EGraphicalElementType.TRIANGLE);
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectText() {

        try {
            selectedGraphicalElement = GraphicalElementFactory.createElement(EGraphicalElementType.TEXT_FIELD);
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    // draw the element at the position of the user's touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        //TODO: Für Implementierung von Freehand-Drawing:
        // if-statement einfügen, damit der nächste Absatz nur aufgerufen wird,
        // wenn zuvor eine Shape im Menü angewählt wurde.

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (selectedGraphicalElement != null) {
                drawnElements.add(selectedGraphicalElement);

                // füge Klickposition (touchX, touchY) an das letzte Objekt in drawnShapes
                //TODO: last element
                getLastElement().setxPosition(touchX);
                getLastElement().setyPosition(touchY);

                invalidate();
                return true;
            }
            else {
                Log.w("CanvasView", "No object selected");
                return false;
            }

        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // füge Klickposition (touchX, touchY) an das letzte Objekt in drawnShapes
            getLastElement().setxPosition(touchX);
            getLastElement().setyPosition(touchY);

            invalidate();
            return true;

        } else {
            return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        super.onDraw(mCanvas);

        for (GraphicalElement graphicalElement : drawnElements) {
            graphicalElement.draw(canvas);
            //siehe strategy pattern
        }
    }

    public void clear() {
        drawnElements.clear();
        invalidate();
    }

}
