package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;

@RunWith(MockitoJUnitRunner.class)
public class DrawQuadrangleStrategyTest extends TestCase {

    @Mock
    private Canvas canvas;
    @Mock
    private Quadrangle quadrangle;

    @Test
    public void testDrawQuadrangle() {
        DrawQuadrangleStrategy strategy = new DrawQuadrangleStrategy();

        strategy.draw(canvas, quadrangle);

        Mockito.verify(canvas).drawRect(Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.any(Paint.class));
    }
}
