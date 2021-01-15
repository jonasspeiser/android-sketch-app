package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawLineStrategy;

@RunWith(MockitoJUnitRunner.class)
public class LineTest extends TestCase {

    @Mock
    DrawLineStrategy strategy = new DrawLineStrategy();
    Line line = new Line(strategy);

    @Test
    public void testIsWithinElement() {
        line.setStartCoordinates(80, 100);
        line.xPosition = 100;
        line.yPosition = 200;

        //test passes if touch is within line
        assertTrue(line.isWithinElement(80,100));
    }
}