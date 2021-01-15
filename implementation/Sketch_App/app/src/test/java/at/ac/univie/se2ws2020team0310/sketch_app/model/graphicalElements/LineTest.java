package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import org.junit.Test;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawLineStrategy;

import static org.junit.Assert.assertTrue;

public class LineTest {

    @Test
    public void isWithinElement() {
        DrawLineStrategy strategy = new DrawLineStrategy();
        Line line = new Line(strategy);

        line.setStartCoordinates(80, 100);
        line.xPosition = 100;
        line.yPosition = 200;

        //test passes if touch is within circle
        assertTrue(line.isWithinElement(80,100));
    }
}