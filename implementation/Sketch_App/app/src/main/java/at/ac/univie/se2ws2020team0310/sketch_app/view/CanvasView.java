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

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                appViewModel.freehandBehaviourOnTouchDown();
                appViewModel.elementsBehaviourOnTouchDown(touchX, touchY);
                if (appViewModel.elementsToDraw()) {
                    invalidate();
                    return true;
                } else {
                    Log.w("CanvasView", "No object selected");
                    return false;
                }

            case MotionEvent.ACTION_MOVE:
                appViewModel.freehandBehaviourOnTouchMove();
                appViewModel.elementsBehaviourOnTouchMove(touchX, touchY);
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                appViewModel.freehandBehaviourOnTouchUp();
                appViewModel.elementsBehaviourOnTouchUp();
                return true;

            default:
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
        appViewModel.clearSketch();
        invalidate();
    }

    public void deleteElement() {
        appViewModel.deleteElement();
        invalidate();
    }

    public void changeElementColor(int color) {
        appViewModel.changeElementColor(color);
        invalidate();
    }

    public void changeElementStrokeWidth(int strokewidth) {
        appViewModel.changeElementStrokeWidth(strokewidth);
        invalidate();
    }

    public void changeElementTextSize(int textsize) {
        appViewModel.changeElementTextSize(textsize);
        invalidate();
    }

}
