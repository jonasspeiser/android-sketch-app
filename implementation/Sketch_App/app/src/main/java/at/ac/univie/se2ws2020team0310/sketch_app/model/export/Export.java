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

    public boolean exportImage(Context context, Bitmap drawingCache, String fileFormat) throws IOException {
        exportPreparation(context,drawingCache,fileFormat);
        compressImage(drawingCache,fileFormat);
        exportingImage(fileFormat,context);
        return true;
    }

    public boolean exportPreparation(Context context, Bitmap drawingCache, String fileFormat) throws IOException {
        return true;
    }

    public boolean compressImage(Bitmap drawingCache, String fileFormat) throws IOException {
        return true;
    }

    public boolean exportingImage(String fileFormat, Context context) throws IOException {
        return true;
    }

}


