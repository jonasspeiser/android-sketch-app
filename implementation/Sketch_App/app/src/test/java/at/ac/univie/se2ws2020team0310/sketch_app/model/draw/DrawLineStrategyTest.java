package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Line;

@RunWith(MockitoJUnitRunner.class)
public class DrawLineStrategyTest extends TestCase {

    @Mock
    private Canvas canvas;
    @Mock
    private Line line;

    @Test
    public void testDrawLine() {
        DrawLineStrategy strategy = new DrawLineStrategy();

        strategy.draw(canvas, line);

        Mockito.verify(canvas).drawLine(Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.any(Paint.class));
    }
}
