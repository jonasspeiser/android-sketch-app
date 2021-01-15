package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatternInterfaces.CustomObserver;

@RunWith(MockitoJUnitRunner.class)
public class CanvasViewModelTest extends TestCase {
    @Mock
    ArrayList<CustomObserver> observers;
    @Mock
    CustomObserver observer;
    @Mock
    Sketch sketch;
    @Mock
    Context context;
    @Mock
    Bitmap drawingCache;

    CanvasViewModel canvasViewModel = new CanvasViewModel();

    //test on list of drawn elements not being null
    @Test
    public void getDrawnElementsTest() {
        assertNotNull(canvasViewModel.getDrawnElements());
    }

    //test on export functionality
    @Test
    public void exportPNGTest() throws IOException {
        boolean exported = canvasViewModel.export(context, "PNG", drawingCache);
        assertTrue(exported);
    }

}