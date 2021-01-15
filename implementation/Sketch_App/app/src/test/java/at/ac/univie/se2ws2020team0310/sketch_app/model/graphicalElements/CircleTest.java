package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import org.junit.Test;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;

import static org.junit.Assert.assertTrue;

public class CircleTest {

    @Test
    public void testIsWithinElement() {
        DrawCircleStrategy strategy = new DrawCircleStrategy();
        Circle circle = new Circle(strategy);

        circle.xPosition = 100;
        circle.yPosition = 200;
        circle.setRadius(70);

        //test passes if touch is within circle
        assertTrue(circle.isWithinElement(120,210));
    }
}