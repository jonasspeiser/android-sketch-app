package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;

@RunWith(MockitoJUnitRunner.class)
public class TriangleTest extends TestCase {

    @Mock
    DrawTriangleStrategy strategy = new DrawTriangleStrategy();
    Triangle triangle = new Triangle(strategy);

    @Test
    public void testIsWithinElement() {
        triangle.xPosition = 100;
        triangle.yPosition = 200;
        triangle.setSize(150);

        //test passes if touch is within triangle
        assertTrue(triangle.isWithinElement(120,210));
    }
}