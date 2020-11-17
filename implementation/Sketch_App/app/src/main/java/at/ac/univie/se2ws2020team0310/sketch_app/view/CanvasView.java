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

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.EGraphicalElementType;

public class CanvasView extends View {

// Attributes
    // TODO initiate in Constructor and only adapt on size change
    private Bitmap mBitmap;
    private Canvas mCanvas;

    private List <GraphicalElement> drawnElements = new ArrayList<>();

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

// Methods

    void init(@Nullable AttributeSet set) {
        //TODO: Paint Objekt wirklich hier initiieren??

        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setAntiAlias(true);
        GraphicalElement.setSelectedPaint(mPaint);
    }

    public void selectCircle() {
        // initiates canvas-object, constructs circle-object, adds circle-object to the draw-list
        // and invalidates the view, so that everything gets drawn

        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);


        Circle mCircle = new Circle();
        // TODO: Implement another constructor for Circle-Class
        //  and put following paragraph into a constructor call
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mCircle.setObjectPaint(mPaint);
        mCircle.setShapeSize(70);
        //mCircle.setmCircleX(getWidth() / 2); // center horizontally
        //mCircle.setmCircleY(getHeight() / 2); // center vertically
        mCircle.setxPosition(mCircle.generateRandomX(mCanvas));
        mCircle.setyPosition(mCircle.generateRandomY(mCanvas));

        drawnElements.add(mCircle);
    }

    public void selectQuadrangle() {
        // TODO remove following block of code from here
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        /*
        Quadrangle mSquare = new Quadrangle();
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mSquare.setObjectPaint(mPaint);
        mSquare.setShapeSize(150);
        mSquare.setLength(mSquare.getShapeSize());
        mSquare.setHeight(mSquare.getShapeSize());
        mSquare.setxPosition(mSquare.generateRandomX(mCanvas));
        mSquare.setyPosition(mSquare.generateRandomY(mCanvas));
         */
        // use a Factory to create the Quadrangle as a GraphicalElement
        try {
            GraphicalElement mSquare =
                    GraphicalElementFactory.createElement(EGraphicalElementType.QUADRANGLE, mCanvas);
            drawnElements.add(mSquare);
            invalidate();
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectText() {

        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        Text mText = new Text();
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mText.setObjectPaint(mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);


        //mText.setxPosition(getWidth() / 2);
        //mText.setyPosition(getHeight() / 2);


        drawnElements.add(mText);

        invalidate();

    }


    // draw the element at the position of the user's touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        //TODO: if-statement einfügen, damit der nächste Absatz nur aufgerufen wird,
        // wenn zuvor eine Shape im Menü angewählt wurde.
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // füge Klickposition (touchX, touchY) an das letzte Objekt in drawnShapes
            GraphicalElement lastElement = drawnElements.get(drawnElements.size() - 1);
            lastElement.setxPosition(touchX);
            lastElement.setyPosition(touchY);

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

        // Iterator pattern is already implemented in the List interface
        // we could replace it with our own Iterator
        for (GraphicalElement graphicalElement : drawnElements) {
            if(graphicalElement instanceof Circle) {
                canvas.drawCircle(graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getShapeSize(), graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Quadrangle) {
                canvas.drawRect(graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getxPosition() + ((Quadrangle) graphicalElement).getLength(), graphicalElement.getyPosition() + ((Quadrangle) graphicalElement).getHeight(), graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Text) {
                canvas.drawText("Hello", graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
            }
        }
    }


}
