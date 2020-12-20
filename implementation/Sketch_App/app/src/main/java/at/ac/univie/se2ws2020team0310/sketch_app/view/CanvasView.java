package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import at.ac.univie.se2ws2020team0310.sketch_app.viewmodel.AppViewModel;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class CanvasView extends View {

    private Canvas mCanvas;

    private AppViewModel appViewModel = new AppViewModel();

// Constructors
    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

// Getters and Setters

    public AppViewModel getAppViewModel() {
        return appViewModel;
    }

// Methods

    @Override
    protected void onSizeChanged(int width, int height, int old_width, int old_height) {
        Bitmap mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
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
            if (appViewModel.getSelectedGraphicalElement() != null) {
                appViewModel.storeElement();

                // füge Klickposition (touchX, touchY) an das letzte Objekt in drawnShapes
                appViewModel.changeCoordinates(touchX, touchY);

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
            appViewModel.changeCoordinates(touchX, touchY);

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

        for (GraphicalElement graphicalElement : appViewModel.getDrawnElements()) {
            graphicalElement.draw(canvas);
        }
    }

    public void clear() {
        appViewModel.clear();
        invalidate();
    }

    public void changeElementColor(int color) {
        appViewModel.changeColor(color);
    }

    public void changeElementStrokeWidth(int strokewidth) {
        appViewModel.changeStrokeWidth(strokewidth);
    }

    public void changeElementTextSize(int textsize) {
        appViewModel.changeTextSize(textsize);
    }

}
