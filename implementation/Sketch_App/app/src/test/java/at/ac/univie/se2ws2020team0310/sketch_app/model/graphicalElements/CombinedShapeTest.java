package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import android.graphics.Color;
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCombinedShapeStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CombinedShapeTest {

    private static final float CIRCLE_X_POSITION = 100;
    private static final float CIRCLE_Y_POSITION = 200;
    private static final float CIRCLE_RADIUS = 70;
    private static final float QUADRANGLE_X_POSITION = 120;
    private static final float QUADRANGLE_Y_POSITION = 220;
    private static final float QUADRANGLE_LENGTH = 80;
    private static final float QUADRANGLE_HEIGHT = 20;
    private static final float DELTA = 0.01F;
    private static final float CIRCLE_SIZE = 20;
    private static final int CIRCLE_STROKE_WIDTH = 30;
    private static final float QUADRANGLE_SIZE = 10.5F;
    private static final float QUADRANGLE_STROKE_WIDTH = 21.3F;

    @Mock
    private DrawCombinedShapeStrategy strategy;

    private CombinedShape combinedShape;
    private List<GraphicalElement> elements;
    private Circle circle;
    private Quadrangle quadrangle;
    private Line line;

    /**
     * Create a new CombinedShape and 3 GraphicalElements to test: Circle, Quadrangle, Line
     */
    @Before
    public void setup() {
        combinedShape = new CombinedShape(strategy);
        combinedShape.setXPosition(0);
        combinedShape.setYPosition(0);
        circle = new Circle(new DrawCircleStrategy());
        circle.setXPosition(CIRCLE_X_POSITION);
        circle.setYPosition(CIRCLE_Y_POSITION);
        circle.setRadius(CIRCLE_RADIUS);
        circle.setColor(Color.BLACK);
        circle.setSize(CIRCLE_SIZE);
        circle.setStrokeWidth(CIRCLE_STROKE_WIDTH);
        quadrangle = new Quadrangle(new DrawQuadrangleStrategy());
        quadrangle.setXPosition(QUADRANGLE_X_POSITION);
        quadrangle.setYPosition(QUADRANGLE_Y_POSITION);
        quadrangle.setLength(QUADRANGLE_LENGTH);
        quadrangle.setHeight(QUADRANGLE_HEIGHT);
        quadrangle.setColor(Color.BLACK);
        quadrangle.setSize(QUADRANGLE_SIZE);
        quadrangle.setStrokeWidth(QUADRANGLE_STROKE_WIDTH);
        line = new Line(new DrawLineStrategy());
        line.setStartCoordinates(80, 100);
        line.setXPosition(100);
        line.setYPosition(200);
    }

    /**
     * Test that the coordinates are within the CombinedShape
     *
     * @throws AppException thrown by add(), in case there is a duplicate
     */
    @Test
    public void testIsWithinElementTrue() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);

        assertTrue(combinedShape.isWithinElement(130, 221));
    }

    /**
     * Test that the coordinates are not within the CombinedShape
     *
     * @throws AppException thrown by add(), in case there is a duplicate
     */
    @Test
    public void testIsWithinElementFalse() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);

        assertFalse(combinedShape.isWithinElement(100, 100));
    }

    /**
     * Test that the 2 GraphicalElements are included in CombinedShape
     *
     * @throws AppException thrown by add(), in case there is a duplicate
     */
    @Test
    public void testAddTwoElements() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);

        assertTrue(combinedShape.getElements().contains(circle));
        assertTrue(combinedShape.getElements().contains(quadrangle));
    }

    /**
     * Test that Line is not included in CombinedShape
     *
     * @throws AppException thrown by add(), in case there is a duplicate
     */
    @Test
    public void testLineNotInElements() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);

        assertFalse(combinedShape.getElements().contains(line));
    }

    /**
     * Test that adding a GraphicalElement twice will throw an AppException
     */
    @Test
    public void testAddElementTwiceThrowsAppException() {
        assertThrows(AppException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                combinedShape.add(circle);
                combinedShape.add(circle);
            }
        });
    }

    /**
     * Test that settings coordinates on an empty list will throw an AppException
     */
    @Test
    public void testSetCoordinatesEmptyElements() {
        float nextX = 150;
        float nextY = 250;

        assertThrows(AppException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                combinedShape.setCoordinates(nextX, nextY);
            }
        });
    }

    /**
     * Test setting coordinates for the CombinedShape will change coordinates for each
     * GraphicalElement with the same offset
     *
     * @throws AppException thrown if the list of elements is empty
     */
    @Test
    public void testSetCoordinates() throws AppException {
        float nextX = 150;
        float nextY = 260;
        float offsetX = nextX - CIRCLE_X_POSITION;
        float offsetY = nextY - CIRCLE_Y_POSITION;
        combinedShape.add(circle);
        combinedShape.add(quadrangle);
        combinedShape.setCoordinates(nextX, nextY);

        assertEquals(CIRCLE_X_POSITION + offsetX, circle.getXPosition(), DELTA);
        assertEquals(CIRCLE_Y_POSITION + offsetY, circle.getYPosition(), DELTA);
        assertEquals(QUADRANGLE_X_POSITION + offsetX, quadrangle.getXPosition(), DELTA);
        assertEquals(QUADRANGLE_Y_POSITION + offsetY, quadrangle.getYPosition(), DELTA);
    }

    /**
     * Test getting the X coordinate on empty list
     */
    @Test
    public void testGetXPositionEmptyElements() {
        assertEquals(0, combinedShape.getXPosition(), DELTA);
    }

    /**
     * Test getting the X coordinate with circle and quadrangle
     * will return the X coordinate of the first element - circle
     */
    @Test
    public void testGetXPositionFromCircle() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);

        assertEquals(CIRCLE_X_POSITION, combinedShape.getXPosition(), DELTA);
    }

    /**
     * Test getting the Y coordinate on empty list
     */
    @Test
    public void testGetYPositionEmptyElements() {
        assertEquals(0, combinedShape.getYPosition(), DELTA);
    }

    /**
     * Test getting the Y coordinate with circle and quadrangle
     * will return the Y coordinate of the first element - circle
     */
    @Test
    public void testGetYPositionFromCircle() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);

        assertEquals(CIRCLE_Y_POSITION, combinedShape.getYPosition(), DELTA);
    }

    /**
     * Test same color is set to all GraphicalElements
     * @throws AppException if trying to add an existing element
     */
    @Test
    public void testSetColor() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);
        int blue = Color.BLUE;

        combinedShape.setColor(blue);

        assertEquals(blue, circle.getColor());
        assertEquals(blue, quadrangle.getColor());
    }

    /**
     * Test same stroke width is set to all GraphicalElements
     * @throws AppException if trying to add an existing element
     */
    @Test
    public void testSetStrokeWidth() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);
        float strokeWidth = 15.5F;

        combinedShape.setStrokeWidth(strokeWidth);

        assertEquals(strokeWidth, circle.getStrokeWidth(), DELTA);
        assertEquals(strokeWidth, quadrangle.getStrokeWidth(), DELTA);
    }

    /**
     * Test same size is set to all GraphicalElements
     * @throws AppException if trying to add an existing element
     */
    @Test
    public void testSetSize() throws AppException {
        combinedShape.add(circle);
        combinedShape.add(quadrangle);
        float size = 46.5F;

        combinedShape.setSize(size);

        assertEquals(size, circle.getSize(), DELTA);
        assertEquals(size, quadrangle.getSize(), DELTA);
    }
}
