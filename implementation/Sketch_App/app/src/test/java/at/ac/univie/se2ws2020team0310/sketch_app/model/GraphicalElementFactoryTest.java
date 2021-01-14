package at.ac.univie.se2ws2020team0310.sketch_app.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import android.graphics.Color;
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCombinedShapeStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawFreehandStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTextStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Circle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Line;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Triangle;
import org.junit.Before;
import org.junit.Test;

public class GraphicalElementFactoryTest {

    private static final double DELTA = 0.01D;
    private int color;
    private float size;
    private float strokeWidth;

    @Before
    public void setup() {
        color = Color.BLUE;
        size = 20;
        strokeWidth = 25;
    }

    /**
     * Create a Line using the GraphicalElementFactory and test its fields
     * @throws AppException in case the EGraphicalElementType is not valid
     */
    @Test
    public void testCreateLine() throws AppException {
        GraphicalElement element = GraphicalElementFactory
                .createElement(EGraphicalElementType.LINE, color, size, strokeWidth);

        assertTrue(element instanceof Line);
        assertTrue(element.getDrawStrategy() instanceof DrawLineStrategy);
        Line line = (Line) element;
        assertEquals(color, line.getColor());
        // Line has no size
        // delta parameter is required by the assertEquals method
        assertEquals(0, line.getSize(), DELTA);
        assertEquals(strokeWidth, line.getStrokeWidth(), DELTA);
    }

    @Test
    public void testCreateCircle() throws AppException {
        GraphicalElement element = GraphicalElementFactory
                .createElement(EGraphicalElementType.CIRCLE, color, size, strokeWidth);

        assertTrue(element instanceof Circle);
        assertTrue(element.getDrawStrategy() instanceof DrawCircleStrategy);
        Circle circle = (Circle) element;
        assertEquals(color, circle.getColor());
        // delta parameter is required by the assertEquals method
        assertEquals(size, circle.getSize(), DELTA);
        assertEquals(size / 2, circle.getRadius(), DELTA);
        assertEquals(strokeWidth, circle.getStrokeWidth(), DELTA);
    }

    @Test
    public void testCreateFreehand() throws AppException {
        GraphicalElement element = GraphicalElementFactory
                .createElement(EGraphicalElementType.FREEHAND, color, size, strokeWidth);

        assertTrue(element instanceof Freehand);
        assertTrue(element.getDrawStrategy() instanceof DrawFreehandStrategy);
        Freehand freehand = (Freehand) element;
        assertEquals(color, freehand.getColor());
        // delta parameter is required by the assertEquals method
        assertEquals(size, freehand.getSize(), DELTA);
        assertEquals(strokeWidth, freehand.getStrokeWidth(), DELTA);
    }

    @Test
    public void testCreateCombinedShape() throws AppException {
        GraphicalElement element = GraphicalElementFactory
                .createElement(EGraphicalElementType.COMBINED_SHAPE, color, size, strokeWidth);

        assertTrue(element instanceof CombinedShape);
        assertTrue(element.getDrawStrategy() instanceof DrawCombinedShapeStrategy);
        CombinedShape combinedShape = (CombinedShape) element;
        assertEquals("CombiShape", combinedShape.getName());
        assertTrue(combinedShape.getElements().isEmpty());
    }

    @Test
    public void testCreateTriangle() throws AppException {
        GraphicalElement element = GraphicalElementFactory
                .createElement(EGraphicalElementType.TRIANGLE, color, size, strokeWidth);

        assertTrue(element instanceof Triangle);
        assertTrue(element.getDrawStrategy() instanceof DrawTriangleStrategy);
        Triangle triangle = (Triangle) element;
        assertEquals(color, triangle.getColor());
        // delta parameter is required by the assertEquals method
        assertEquals(size, triangle.getSize(), DELTA);
        assertEquals(strokeWidth, triangle.getStrokeWidth(), DELTA);
    }

    @Test
    public void testCreateQuadrangle() throws AppException {
        GraphicalElement element = GraphicalElementFactory
                .createElement(EGraphicalElementType.QUADRANGLE, color, size, strokeWidth);

        assertTrue(element instanceof Quadrangle);
        assertTrue(element.getDrawStrategy() instanceof DrawQuadrangleStrategy);
        Quadrangle quadrangle = (Quadrangle) element;
        assertEquals(color, quadrangle.getColor());
        // delta parameter is required by the assertEquals method
        assertEquals(size, quadrangle.getSize(), DELTA);
        assertEquals(size, quadrangle.getHeight(), DELTA);
        assertEquals(size, quadrangle.getLength(), DELTA);
        assertEquals(strokeWidth, quadrangle.getStrokeWidth(), DELTA);
    }

    @Test
    public void testCreateText() throws AppException {
        GraphicalElement element = GraphicalElementFactory
                .createElement(EGraphicalElementType.TEXT_FIELD, color, size, strokeWidth);

        assertTrue(element instanceof Text);
        assertTrue(element.getDrawStrategy() instanceof DrawTextStrategy);
        Text text = (Text) element;
        assertEquals(color, text.getColor());
        // delta parameter is required by the assertEquals method
        assertEquals(size, text.getSize(), DELTA);
        assertEquals("", text.getUserText());
    }

    /**
     * Create a copy of a Circle element and test its fields
     */
    @Test
    public void testCreateElementCopy() {
        Circle originalCircle = new Circle(new DrawCircleStrategy());
        originalCircle.setColor(color);
        originalCircle.setSize(size);
        originalCircle.setStrokeWidth(strokeWidth);
        GraphicalElement element = GraphicalElementFactory.createElement(originalCircle);

        // first check that the original circle and the returned element are not the same object
        assertNotEquals(originalCircle, element);
        assertTrue(element instanceof Circle);
        assertTrue(element.getDrawStrategy() instanceof DrawCircleStrategy);
        Circle circle = (Circle) element;
        assertEquals(color, circle.getColor());
        // delta parameter is required by the assertEquals method
        assertEquals(size, circle.getSize(), DELTA);
        assertEquals(size / 2, circle.getRadius(), DELTA);
        assertEquals(strokeWidth, circle.getStrokeWidth(), DELTA);
    }

}
