package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.viewmodel.CanvasViewModel;

public class CanvasView extends View {

// Attributes

    public Bitmap mBitmap;
    private Canvas mCanvas;
    private final CanvasViewModel canvasViewModel = new CanvasViewModel();

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

    public CanvasViewModel getCanvasViewModel() {
        return canvasViewModel;
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
                canvasViewModel.onTouchDown(touchX, touchY);
                if (canvasViewModel.elementsToDraw()) {
                    invalidate();
                    return true;
                } else {
                    Log.w("CanvasView", "No object selected");
                    return false;
                }

            case MotionEvent.ACTION_MOVE:
                canvasViewModel.onTouchMove(touchX, touchY);
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                canvasViewModel.onTouchUp();
                return true;

            default:
                return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        super.onDraw(mCanvas);

        for (GraphicalElement graphicalElement : canvasViewModel.getDrawnElements()) { // TODO: Logik -> das muss ins ViewModel
            graphicalElement.draw(canvas);
        }
    }

    public void clear() {
        canvasViewModel.clearSketch();
        invalidate();
    }

    public void deleteElement() {
        canvasViewModel.deleteElement();
        invalidate();
    }

    public void changeElementColor(int color) {
        canvasViewModel.changeElementColor(color);
        invalidate();
    }

    public void changeElementStrokeWidth(int strokewidth) {
        canvasViewModel.changeElementStrokeWidth(strokewidth);
        invalidate();
    }

    public void changeElementSize(int size) {
        canvasViewModel.changeElementSize(size);
        invalidate();
    }

    public void saveToInternalStorage() {

   /* this.setDrawingCacheEnabled(true);

        String imgSaved = MediaStore.Images.Media.insertImage(
                getContentResolver(), this.getDrawingCache(),
                UUID.randomUUID().toString()+".png", "drawing");

        if(imgSaved!=null){
            Toast savedToast = Toast.makeText(getApplicationContext(),
                    "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
            savedToast.show();
        }
        else{
            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                    "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
            unsavedToast.show();*/
    }
}
/*    }



    }*/
  /*      AlertDialog.Builder saveDialog = new AlertDialog.Builder();
        saveDialog.setTitle("Save drawing");
        saveDialog.setMessage("Save drawing to device Gallery?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //save drawing
            }
        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        saveDialog.show();*/


