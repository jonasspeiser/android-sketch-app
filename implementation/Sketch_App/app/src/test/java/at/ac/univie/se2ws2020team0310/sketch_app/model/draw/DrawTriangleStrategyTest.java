package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Triangle;

@RunWith(MockitoJUnitRunner.class)
public class DrawTriangleStrategyTest extends TestCase {

    @Mock
    private Canvas canvas;
    @Mock
    private Triangle triangle;

    @Test
    public void testDrawTriangle() {
        DrawTriangleStrategy strategy = new DrawTriangleStrategy();

        strategy.draw(canvas, triangle);

        Mockito.verify(canvas).drawPath(Mockito.any(Path.class),
                Mockito.any(Paint.class));
    }
}
