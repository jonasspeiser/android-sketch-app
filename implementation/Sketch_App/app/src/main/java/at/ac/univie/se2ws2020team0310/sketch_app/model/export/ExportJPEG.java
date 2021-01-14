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

    //ToDo warum werden im Konstruktor Argumente übergeben, die nicht verwendet werden?
    public ExportJPEG(Context context, String fileFormat, Bitmap drawingCache) {

    }

    ;

    @Override
    public void compressImage(Bitmap drawingCache, String fileFormat) throws IOException {
        drawingCache.compress(Bitmap.CompressFormat.JPEG, 100, this.out);
        Log.d("Compression", "Compression in " + fileFormat + " successful.");
    }

}
