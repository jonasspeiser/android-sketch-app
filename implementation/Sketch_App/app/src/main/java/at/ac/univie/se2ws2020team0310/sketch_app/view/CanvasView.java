package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.R;

public class CanvasView extends View {

// Attributes
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Drawing drawing;
    Path mPath = new Path();
   // private Path mPath;
   // private Paint mPaint;

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

        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
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

        invalidate();
    }

    public void selectQuadrangle() {

        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        Quadrangle mSquare = new Quadrangle();
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mSquare.setObjectPaint(mPaint);
        mSquare.setShapeSize(150);
        mSquare.setLength(mSquare.getShapeSize());
        mSquare.setHeight(mSquare.getShapeSize());
        mSquare.setxPosition(mSquare.generateRandomX(mCanvas));
        mSquare.setyPosition(mSquare.generateRandomY(mCanvas));

        drawnElements.add(mSquare);

        invalidate();

    }
    
    public void selectText() {

        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        Text mText = new Text();
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mText.setObjectPaint(mPaint);

        mText.setyPosition(mText.generateRandomX(mCanvas)); // center horizontally
        mText.setyPosition(mText.generateRandomY(mCanvas)); // center vertically


        drawnElements.add(mText);

        invalidate();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        super.onDraw(mCanvas);

        for (GraphicalElement graphicalElement : drawnElements) {
            if(graphicalElement instanceof Circle) {
                canvas.drawCircle(graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getShapeSize(), graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Quadrangle) {
                canvas.drawRect(graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getxPosition() + ((Quadrangle) graphicalElement).getLength(), graphicalElement.getyPosition() + ((Quadrangle) graphicalElement).getHeight(), graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Drawing) {
                canvas.drawBitmap(mBitmap, 0 , 0, graphicalElement.getObjectPaint());
                canvas.drawPath(((Drawing) graphicalElement).getObjectPath(),graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Text) {
                canvas.drawText("Hello", graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
            }

        }

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                mPath.lineTo(touchX, touchY);

                drawing = new Drawing();
                drawing.setObjectPaint(GraphicalElement.getSelectedPaint());
                mCanvas.drawPath(mPath, drawing.getObjectPaint());
                drawing.setObjectPath(mPath);
                drawnElements.add(drawing);
                //mPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

}
