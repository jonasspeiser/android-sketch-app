package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DrawTextStrategyTest {

    @Mock
    private Canvas canvas;
    @Mock
    private Text text;

    @Test
    public void testDrawText() {
        DrawTextStrategy strategy = new DrawTextStrategy();

        strategy.draw(canvas, text);

        Mockito.verify(canvas).drawText(Mockito.anyString(), Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.any(Paint.class));
    }
}
