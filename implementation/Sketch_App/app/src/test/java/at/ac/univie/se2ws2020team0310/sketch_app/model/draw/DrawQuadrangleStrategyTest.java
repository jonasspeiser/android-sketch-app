package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import static org.junit.Assert.*;

import android.graphics.Canvas;
import android.graphics.Paint;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DrawQuadrangleStrategyTest {

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
