package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Circle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DrawCircleStrategyTest {

    @Mock
    private Canvas canvas;

    @Mock
    private Circle circle;

    @Test
    public void testDrawCircle() {
        DrawCircleStrategy strategy = new DrawCircleStrategy();

        strategy.draw(canvas, circle);

        // verify the canvas.drawCircle was invoked
        Mockito.verify(canvas)
                .drawCircle(Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyFloat(),
                        Mockito.any(Paint.class));

    }
}
