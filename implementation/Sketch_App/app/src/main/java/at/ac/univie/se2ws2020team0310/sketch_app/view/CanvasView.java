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
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatternInterfaces.CustomObserver;
import at.ac.univie.se2ws2020team0310.sketch_app.viewmodel.CanvasViewModel;
import java.io.IOException;

public class CanvasView extends View implements CustomObserver {

// Attributes

    private Canvas mCanvas;
    private final CanvasViewModel canvasViewModel = new CanvasViewModel();

    // Constructors
    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        canvasViewModel.registerObserver(this);
    }

// Methods

    @Override
    protected void onSizeChanged(int width, int height, int old_width, int old_height) {
        Bitmap mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    /**
     * Draw or update the element at the position of the user's touch
     *
     * @param event the user Touch Event
     * @return true, in case the event action was handled without errors
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    canvasViewModel.onTouchDown(touchX, touchY);
                    if (canvasViewModel.elementsToDraw()) {
                        return true;
                    } else {
                        throw new AppException("No object selected");
                    }

                case MotionEvent.ACTION_MOVE:
                    canvasViewModel.onTouchMove(touchX, touchY);
                    return true;

                case MotionEvent.ACTION_UP:
                    canvasViewModel.onTouchUp();
                    return true;

                default:
                    return false;
            }
        } catch (AppException ex) {
            // Error Handling: any AppException caused by user Touch actions will be caught and handled here
            ViewUtils.showToast(getContext(), ex.getLocalizedMessage());
            Log.w("CanvasView", ex.getLocalizedMessage());
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        super.onDraw(mCanvas);

        for (GraphicalElement graphicalElement : canvasViewModel
                .getDrawnElements()) { // TODO: Logik -> das muss ins Model
            graphicalElement.draw(canvas);
        }
    }

    public boolean export(Context context, String fileFormat)
            throws IOException {    // TODO: Move to MainActivityViewModel, here there should only be a method which passes the drawingCache
        canvasViewModel.export(context, fileFormat, this.getDrawingCache());
        return true;
    }

    @Override
    public void update() {
        invalidate();
    }

    public GraphicalElement getSelectedGraphicalElement() {
        return canvasViewModel.getSelectedGraphicalElement();
    }

}
