package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;

@RunWith(MockitoJUnitRunner.class)
public class CircleTest extends TestCase {

    @Mock
    DrawCircleStrategy strategy = new DrawCircleStrategy();
    Circle circle = new Circle(strategy);

    @Test
    public void testIsWithinElement() {
        circle.xPosition = 100;
        circle.yPosition = 200;
        circle.setRadius(70);

        //test passes if touch is within circle
        assertTrue(circle.isWithinElement(120,210));
    }
}