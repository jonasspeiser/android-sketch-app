package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
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
    public static Canvas mCircleCanvas;
    public Paint mCirclePaint;


    private List <Circle> drawnShapes = new ArrayList<Circle>(); // das ist nur ein Test,
    // Feldlänge später dynamisch festlegen.

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
        // put circle into list
        // call drawShape(variable)
        Circle mCircle = new Circle();
        mCircle.setmCircleRadius(50);
        mCircle.setmCirclePaint(mCirclePaint);
        //mCircle.setmCircleX(getWidth() / 2); // center horizontally
        //mCircle.setmCircleY(getHeight() / 2); // center vertically
        mCircle.setmCircleX(mCircle.generateRandomWidth(mCircleCanvas));
        mCircle.setmCircleY(mCircle.generateRandomHeight(mCircleCanvas));


        drawnShapes.add(mCircle);

        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mCircleCanvas = canvas;
        super.onDraw(mCircleCanvas);

        for (Circle circle:drawnShapes) {
            canvas.drawCircle(circle.getmCircleX(), circle.getmCircleY(), circle.getmCircleRadius(), circle.getmCirclePaint());
        }
    }


}
