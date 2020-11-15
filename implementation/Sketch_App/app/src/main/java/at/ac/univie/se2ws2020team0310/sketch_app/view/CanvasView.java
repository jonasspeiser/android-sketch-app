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
    public Canvas mCircleCanvas;

    public Paint mCirclePaint;


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

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);

    }

    public void selectCircle() {
        // initiates canvas-object, constructs circle-object, adds circle-object to the draw-list
        // and invalidates the view, so that everything gets drawn

        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCircleCanvas = new Canvas(mBitmap);

        Circle mCircle = new Circle();
        // TODO: Implement another constructor for Circle-Class
        //  and put following paragraph into a constructor call
        mCircle.setSize(50);
        mCircle.setmPaint(mCirclePaint);
        //mCircle.setmCircleX(getWidth() / 2); // center horizontally
        //mCircle.setmCircleY(getHeight() / 2); // center vertically
        mCircle.setxPosition(mCircle.generateRandomX(mCircleCanvas));
        mCircle.setyPosition(mCircle.generateRandomY(mCircleCanvas));

        drawnShapes.add(mCircle);

        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mCircleCanvas = canvas;
        super.onDraw(mCircleCanvas);

        for (Shape shape:drawnShapes) {
            if(shape instanceof Circle) {
                canvas.drawCircle(shape.getxPosition(), shape.getyPosition(), shape.getSize(), shape.getmPaint());
            }
        }
    }


}
