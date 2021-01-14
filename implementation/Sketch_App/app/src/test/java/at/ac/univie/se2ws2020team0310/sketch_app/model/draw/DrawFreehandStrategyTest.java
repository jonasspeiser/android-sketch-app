package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DrawFreehandStrategyTest {

    @Mock
    private Canvas canvas;
    @Mock
    private Freehand freehand;

    @Test
    public void testDrawFreehand() {
        DrawFreehandStrategy strategy = new DrawFreehandStrategy();

        strategy.draw(canvas, freehand);

        Mockito.verify(canvas).drawPath(Mockito.any(Path.class), Mockito.any(Paint.class));

    }
}
