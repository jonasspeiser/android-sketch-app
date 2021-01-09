package at.ac.univie.se2ws2020team0310.sketch_app.model.export;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportJPEG extends Export {

    public FileOutputStream out;
    public File saveImage;

    public ExportJPEG(Context context,String fileFormat,Bitmap drawingCache) {

    };

    @Override
    public boolean exportPreparation(Context context, Bitmap drawingCache, String fileFormat) throws IOException {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        this.saveImage = new File(path,(System.currentTimeMillis() + "." + fileFormat));
        this.saveImage.createNewFile();
        this.out = new FileOutputStream(saveImage);
        Log.d("Export","preparation " + fileFormat + " successful.");
        return true;
    }

    @Override
    public boolean compressImage(Bitmap drawingCache, String fileFormat) throws IOException {
        drawingCache.compress(Bitmap.CompressFormat.JPEG, 100, this.out);
        Log.d("Compression","Compression in " + fileFormat + " successful.");
        return true;
    }

    @Override
    public boolean exportingImage(String fileFormat, Context context) throws IOException {
        this.out.close();
        Log.d("Exporting","Exporting in " + fileFormat + " successful.");
        MediaScannerConnection.scanFile(context, new String[]{this.saveImage.getPath()}, null, null);
        return true;
    }
}
