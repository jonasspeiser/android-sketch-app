package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;

@RunWith(MockitoJUnitRunner.class)
public class QuadrangleTest extends TestCase {

    @Mock
    DrawQuadrangleStrategy strategy = new DrawQuadrangleStrategy();
    Quadrangle quadrangle = new Quadrangle(strategy);

    @Test
    public void testIsWithinElement() {
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