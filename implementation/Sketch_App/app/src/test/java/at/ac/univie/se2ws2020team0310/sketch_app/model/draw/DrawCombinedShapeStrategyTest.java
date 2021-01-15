package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DrawCombinedShapeStrategyTest extends TestCase {

    // Mock collaborators of DrawCombinedShapeStrategy
    @Mock
    private Canvas canvas;
    @Mock
    private CombinedShape combinedShape;
    @Mock
    private GraphicalElement element;


    private DrawCombinedShapeStrategy strategy = new DrawCombinedShapeStrategy();

    @Test
    public void testInitializePaintNull() {
        assertNull(strategy.initializePaint(element));
    }

    @Test
    public void testDrawCombinedShape() throws AppException {
        List<GraphicalElement> elements = new ArrayList<>();
        elements.add(element);
        when(combinedShape.getElements()).thenReturn(elements);

        strategy.draw(canvas, combinedShape);

        // verify that the method goes through the List of GraphicalElements of this CombinedShape
        Mockito.verify(combinedShape).getElements();
        for (GraphicalElement el : combinedShape.getElements()) {
            // verify that the method draw is called for each element
            Mockito.verify(el).draw(canvas);
        }
    }
}
