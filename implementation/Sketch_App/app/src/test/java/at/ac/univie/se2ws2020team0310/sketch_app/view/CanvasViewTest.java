package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class CanvasViewTest extends TestCase {

    @Mock
    CanvasView canvasView;
    @Mock
    Context context;

    //Test on unsupported fileformat selection
    @Test
    public void testExport() throws IOException {
        assertFalse(canvasView.export(context,"PDF"));
    }
}

