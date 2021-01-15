package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Path;

import org.junit.Test;
import org.mockito.Mock;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawFreehandStrategy;

public class FreehandTest {

    @Mock
    DrawFreehandStrategy strategy = new DrawFreehandStrategy();
    Freehand freehand = new Freehand(strategy);
    Path tempPath = new Path(); // Create temp Path


    @Test
    public void testIsWithinElement() {
        /*
        line.setStartCoordinates(80, 100);
        line.xPosition = 100;
        line.yPosition = 200;

        assertTrue(line.isWithinElement(80,100));

         */
    }

}