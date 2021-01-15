package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import org.junit.Test;
import org.mockito.Mock;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;

import static org.junit.Assert.assertTrue;

public class TriangleTest {

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