package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View {

// Attributes
    private Bitmap mBitmap;
    public Canvas mCanvas;

    public Paint mPaint;


    private List <Shape> drawnShapes = new ArrayList<>(); // das ist nur ein Test,

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

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

    }

    public void selectCircle() {
        // initiates canvas-object, constructs circle-object, adds circle-object to the draw-list
        // and invalidates the view, so that everything gets drawn

        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        Circle mCircle = new Circle();
        // TODO: Implement another constructor for Circle-Class
        //  and put following paragraph into a constructor call
        mCircle.setSize(50);
        mCircle.setmPaint(mPaint);
        //mCircle.setmCircleX(getWidth() / 2); // center horizontally
        //mCircle.setmCircleY(getHeight() / 2); // center vertically
        mCircle.setxPosition(mCircle.generateRandomX(mCanvas));
        mCircle.setyPosition(mCircle.generateRandomY(mCanvas));

        drawnShapes.add(mCircle);

        invalidate();
    }

    public void selectQuadrangle() {

        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        Quadrangle mSquare = new Quadrangle();
        mSquare.setSize(150);
        mSquare.setLength(mSquare.getSize());
        mSquare.setHeight(mSquare.getSize());
        mSquare.setmPaint(mPaint);
        mSquare.setxPosition(mSquare.generateRandomX(mCanvas));
        mSquare.setyPosition(mSquare.generateRandomY(mCanvas));

        drawnShapes.add(mSquare);

        invalidate();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        super.onDraw(mCanvas);

        for (Shape shape:drawnShapes) {
            if(shape instanceof Circle) {
                canvas.drawCircle(shape.getxPosition(), shape.getyPosition(), shape.getSize(), shape.getmPaint());
            }
            if(shape instanceof Quadrangle) {
                canvas.drawRect(shape.getxPosition(), shape.getyPosition(), shape.getxPosition() + ((Quadrangle) shape).getLength(), shape.getyPosition() + ((Quadrangle) shape).getHeight(), shape.getmPaint());
            }
        }
    }


}
