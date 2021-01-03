package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import at.ac.univie.se2ws2020team0310.sketch_app.viewmodel.CanvasViewModel;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

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
                canvasViewModel.freehandBehaviourOnTouchDown(touchX, touchY);
                canvasViewModel.elementsBehaviourOnTouchDown(touchX, touchY);
                if (canvasViewModel.elementsToDraw()) {
                    invalidate();
                    return true;
                } else {
                    Log.w("CanvasView", "No object selected");
                    return false;
                }

            case MotionEvent.ACTION_MOVE:
                canvasViewModel.freehandBehaviourOnTouchMove(touchX, touchY);
                canvasViewModel.elementsBehaviourOnTouchMove(touchX, touchY);
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                canvasViewModel.freehandBehaviourOnTouchUp(touchX, touchY);
                canvasViewModel.elementsBehaviourOnTouchUp();
                return true;

            default:
                return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        super.onDraw(mCanvas);

        for (GraphicalElement graphicalElement : canvasViewModel.getDrawnElements()) {
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

    public void saveToInternalStorage(){
        File dir = new File("/sdcard/Pictures/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File output = new File(dir, "tempFile.jpg");
        OutputStream os = null;

        try {
            os = new FileOutputStream(output);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();

            //this code will scan the image so that it will appear in your gallery when you open next time
            MediaScannerConnection.scanFile(this.getContext(), new String[] { output.toString() }, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.d("appname", "image is saved in gallery and gallery is refreshed.");
                        }
                    }
            );
        } catch (Exception e) {
        }
    }

    /*public void saveImage() throws IOException {
        String fileName = "Pikasso" + System.currentTimeMillis();

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");

        // get a URI for the location to save the file
        Uri uri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        OutputStream outputStream =
                getContext().getContentResolver().openOutputStream(uri);

                // copy bitmap to output string created before
                mCanvas.compress(Bitmap.CompressFormat.JPEG,100,outputStream); // this is our image

                outputStream.flush();
                outputStream.close();

        Toast message = new Toast.makeText(getContext(), "Image Saved", Toast.LENGTH_LONG);
            message.setGravity(Gravity.CENTER, message.getXOffset() /2,
                message.getYOffset() /2);
            message.show();

    }*/

}
