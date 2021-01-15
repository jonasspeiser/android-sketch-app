package at.ac.univie.se2ws2020team0310.sketch_app.model.iteratorsAndCollections;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawCircleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawQuadrangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Circle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Triangle;

@RunWith(MockitoJUnitRunner.class)
public class ElementCollectionTest extends TestCase {

    @Mock
    ArrayList<GraphicalElement> collectedElements = new ArrayList<>();

    DrawCircleStrategy drawCircleStrategy = new DrawCircleStrategy();
    Circle circle = new Circle(drawCircleStrategy);
    Object item = circle;

    DrawTriangleStrategy drawTriangleStrategy = new DrawTriangleStrategy();
    GraphicalElement element = new Triangle(drawTriangleStrategy);
    int index = collectedElements.indexOf(element);

    DrawQuadrangleStrategy drawQuadrangleStrategy = new DrawQuadrangleStrategy();
    Quadrangle quadrangle = new Quadrangle(drawQuadrangleStrategy);
    Object item1 = quadrangle;

    @Mock
    ElementCollection elementCollection;

    @Test
    public void testGet() {
        collectedElements.get(index);
        elementCollection.get(index);
        Mockito.verify(collectedElements).get(index);
    }

    @Test
    public void testAdd() {
        collectedElements.add(circle);
        elementCollection.add(item);
        Mockito.verify(collectedElements).add((GraphicalElement) item);
    }

    @Test
    public void testIndexOf() {
        collectedElements.indexOf(item);
        assertEquals(0, collectedElements.indexOf(item));
    }

    @Test
    public void testContains() {
        elementCollection.contains(item1);
        assertFalse(elementCollection.contains(item1));
    }
}