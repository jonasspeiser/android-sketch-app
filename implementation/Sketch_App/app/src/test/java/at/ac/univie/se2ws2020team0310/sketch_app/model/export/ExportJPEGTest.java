package at.ac.univie.se2ws2020team0310.sketch_app.model.export;

import android.graphics.Bitmap;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ExportJPEGTest extends TestCase {

    @Mock
    Bitmap drawingCache;
    @Mock
    Export export;

    @Test
    public void exportJPEGTest() throws IOException {
        export.compressImage(drawingCache,"PDF");
    }

}