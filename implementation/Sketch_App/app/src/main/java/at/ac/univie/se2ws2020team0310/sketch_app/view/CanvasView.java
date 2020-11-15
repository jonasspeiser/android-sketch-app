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

public class CanvasView extends View {

// Attributes
    public static Canvas mCircleCanvas;
    public Paint mCirclePaint;
    private float mCircleX, mCircleY, mCircleRadius;


    private Shape drawnShapes[] = new Shape[5]; // das ist nur ein Test,
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
        float xPosition = getWidth() / 2; // center horizontally
        float yPosition = getHeight() / 2; // center vertically
        float radius = 50;
        Paint paint = mCirclePaint;
        drawShape(xPosition, yPosition, radius, paint);
    }

    public void drawShape(float cx, float cy, float radius, Paint paint) {
        // später: als Parameter ein Objekt vom Typ Kreis übergeben
        // und canvas.drawcircle mit dessen Attributen als Parameter aufrufen
        mCircleX = cx;
        mCircleY = cy;
        mCircleRadius = radius;
        mCirclePaint = paint;
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCircleCanvas = canvas;
        super.onDraw(mCircleCanvas);

        // for shape in drawnShapes[]: canvas.drawCircle
        canvas.drawCircle(mCircleX, mCircleY, mCircleRadius, mCirclePaint);
    }


}
