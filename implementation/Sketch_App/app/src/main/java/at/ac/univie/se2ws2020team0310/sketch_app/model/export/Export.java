package at.ac.univie.se2ws2020team0310.sketch_app.model.export;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class Export {

    public FileOutputStream out;
    public File saveImage;

    public final void exportImage(Context context, Bitmap drawingCache, String fileFormat)
            throws IOException {
        exportPreparation(context, drawingCache, fileFormat);
        compressImage(drawingCache, fileFormat);
        placeImageInGallery(fileFormat, context);
    }

    public void exportPreparation(Context context, Bitmap drawingCache, String fileFormat)
            throws IOException {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        this.saveImage = new File(path, (System.currentTimeMillis() + "." + fileFormat));
        this.out = new FileOutputStream(saveImage);
        Log.d("Export", "preparation " + fileFormat + " successful.");
    }

    public abstract void compressImage(Bitmap drawingCache, String fileFormat) throws IOException;

    public void placeImageInGallery(String fileFormat, Context context) throws IOException {
        this.out.close();
        Log.d("Exporting", "Exporting in " + fileFormat + " successful.");
        MediaScannerConnection
                .scanFile(context, new String[]{this.saveImage.getPath()}, null, null);

    }
}


