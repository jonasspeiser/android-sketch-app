package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import org.junit.Test;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;

import static org.junit.Assert.assertTrue;

public class QuadrangleTest {


    @Test
    public void testIsWithinElement() {
        DrawQuadrangleStrategy strategy = new DrawQuadrangleStrategy();
        Quadrangle quadrangle = new Quadrangle(strategy);

        quadrangle.xPosition = 100;
        quadrangle.yPosition = 200;
        float height = 20;
        float length = 80;
        quadrangle.setLength(length);
        quadrangle.setHeight(height);

        //test passes if touch is within quadrangle
        assertTrue(quadrangle.isWithinElement(100,220));
    }
}