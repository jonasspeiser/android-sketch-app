package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawTextStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawTriangleStrategy;

public class CanvasView extends View {

// Attributes
    // TODO initiate in Constructor and only adapt on size change
    private Bitmap mBitmap;
    private Canvas mCanvas;

    private GraphicalElement selectedGraphicalElement;
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    public GraphicalElement getLastElement() {
        GraphicalElement lastElement = drawnElements.get(drawnElements.size() - 1);
        return lastElement;
    }

    // TODO: Put the select-Methods into Graphical Element and use Polymorphism in the sublasses
    public void selectLine() {

        Line mLine = new Line(new DrawLineStrategy());
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mLine.setObjectPaint(mPaint);

        selectedGraphicalElement = mLine;
    }


    public void selectCircle() {
        // initiates canvas-object, constructs circle-object, adds circle-object to the draw-list
        // and invalidates the view, so that everything gets drawn

        //mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        //mCanvas = new Canvas(mBitmap);


        Circle mCircle = new Circle(new DrawCircleStrategy());
        // TODO: Implement another constructor for Circle-Class
        //  and put following paragraph into a constructor call
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mCircle.setObjectPaint(mPaint);
        mCircle.setShapeSize(70);

        selectedGraphicalElement = mCircle;
    }

    public void selectQuadrangle() {

        /*
        Quadrangle mSquare = new Quadrangle();
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mSquare.setObjectPaint(mPaint);
        mSquare.setShapeSize(150);
        mSquare.setLength(mSquare.getShapeSize());
        mSquare.setHeight(mSquare.getShapeSize());
         */
        // use a Factory to create the Quadrangle as a GraphicalElement
        try {
            GraphicalElement mSquare =
                    GraphicalElementFactory.createElement(EGraphicalElementType.QUADRANGLE, mCanvas);
            selectedGraphicalElement = mSquare;
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectTriangle() {

        Triangle mTriangle = new Triangle(new DrawTriangleStrategy());
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mTriangle.setObjectPaint(mPaint);
        mTriangle.setShapeSize(150);

        selectedGraphicalElement = mTriangle;
    }

    public void selectText() {

        Text mText = new Text(new DrawTextStrategy());
        Paint mPaint = new Paint(GraphicalElement.getSelectedPaint());
        mText.setObjectPaint(mPaint);

        mPaint.setStyle(Paint.Style.FILL);

        selectedGraphicalElement = mText;
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
            else if (selectedGraphicalElement == null) {
                //TODO: throw error message "No object selected"
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

        // Iterator pattern is already implemented in the List interface
        // we could replace it with our own Iterator
        for (GraphicalElement graphicalElement : drawnElements) {
            graphicalElement.draw(canvas);
            // koennte man strategy pattern benutzen
            /*if(graphicalElement instanceof Line) {
                canvas.drawLine(((Line) graphicalElement).getStartX(), ((Line) graphicalElement).getStartY(), graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Circle) {
                canvas.drawCircle(graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getShapeSize(), graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Quadrangle) { //TODO: Berechnung von x und y Koordinaten in Methode in Quadrangle-Klasse auslagern
                canvas.drawRect(graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getxPosition() + ((Quadrangle) graphicalElement).getLength(), graphicalElement.getyPosition() + ((Quadrangle) graphicalElement).getHeight(), graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Triangle) {
                ((Triangle) graphicalElement).drawTriangle(canvas, graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
            }
            if(graphicalElement instanceof Text) {
                canvas.drawText(((Text) graphicalElement).getTextinput(), graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
            }*/

        }
    }

    public void clear() {
        drawnElements.clear();
        invalidate();
    }



}
