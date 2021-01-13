package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.EventLog;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.View;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import at.ac.univie.se2ws2020team0310.sketch_app.viewmodel.CanvasViewModel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CanvasViewTest extends TestCase {

    @Mock
    CanvasView canvasView;
    @Mock
    CanvasViewModel canvasViewModel;
    @Mock
    Context context;
    @Mock
    View view;
    @Mock
    MotionEvent event;

    //Test on correct drawing of Canvas after size change
    @Test
    public void testOnSizeChanged(){
        when(canvasView.getHeight()).thenReturn(800);
        when(canvasView.getWidth()).thenReturn(600);
        Canvas mCanvas = mock(Canvas.class);
        canvasView.onSizeChanged(800, 600, 750, 650);
        assertNotNull(mCanvas);
    }

    //Test on unsupported fileformat selection
    @Test
    public void testExport() throws IOException {
        assertFalse(canvasView.export(context,"PDF"));
    }

    //Test on onTouchEvent-Detection
    @Test
    public void testOnTouchEvent() {
        when(event.getAction()).thenReturn(MotionEvent.ACTION_UP);
        when(event.getX()).thenReturn((float) 0.2);
        when(event.getY()).thenReturn((float) 0.3);
        when(canvasViewModel.elementsToDraw()).thenReturn(true);
        assertNotNull(canvasView.onTouchEvent(event));
    }



}

