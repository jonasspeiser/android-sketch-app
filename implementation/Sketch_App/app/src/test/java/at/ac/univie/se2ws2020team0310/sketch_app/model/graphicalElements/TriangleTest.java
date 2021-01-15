package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import org.junit.Test;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;

import static org.junit.Assert.assertTrue;

public class TriangleTest {

    @Test
    public void isWithinElement() {
        DrawTriangleStrategy strategy = new DrawTriangleStrategy();
        Triangle triangle = new Triangle(strategy);

        triangle.xPosition = 100;
        triangle.yPosition = 200;
        triangle.setSize(150);

        //test passes if touch is within circle
        assertTrue(triangle.isWithinElement(120,210));
    }
}