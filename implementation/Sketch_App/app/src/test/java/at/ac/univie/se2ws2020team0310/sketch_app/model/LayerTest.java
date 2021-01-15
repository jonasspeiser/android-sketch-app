package at.ac.univie.se2ws2020team0310.sketch_app.model;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Circle;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Triangle;

@RunWith(MockitoJUnitRunner.class)
public class LayerTest extends TestCase {

    Layer layer = new Layer();

    @Mock
    Circle circleMock;
    @Mock
    Triangle triangleMock;
    @Mock
    Text textMock;

    @Test
    public void isEmpty_EmptyLayer_ReturnsTrue() {
        assertTrue(layer.isEmpty());
    }

    @Test
    public void isEmpty_LayerWithElement_ReturnsFalse() {
        layer.storeElement(circleMock);
        assertFalse(layer.isEmpty());
    }

    @Test
    public void storeElement_LayerContainsStoredElement() {
        layer.storeElement(circleMock);
        assertTrue(layer.containsElement(circleMock));
    }

    @Test
    public void clear_IsEmpty_ReturnsTrue() {
        layer.storeElement(circleMock);
        layer.storeElement(triangleMock);
        layer.storeElement(textMock);
        layer.clear();
        assertTrue(layer.isEmpty());
    }

    @Test
    public void makeElementOnPositionEditable_ElementHasNewCoordinates() throws AppException {
    }

    @Test
    public void makeElementOnPositionMovable() {
    }

    @Test
    public void makeMovable() {
    }

    @Test
    public void makeEditable() {
    }

    @Test
    public void setCoordinates() {
    }

    @Test
    public void changeCoordinates() {
    }

    @Test
    public void changeColor() {
    }

    @Test
    public void changeStrokeWidth() {
    }

    @Test
    public void changeSize() {
    }

    @Test
    public void deleteEditableElements() {
    }

    @Test
    public void resetEditableElements() {
    }

    @Test
    public void createIterator() {
    }

    @Test
    public void addElementToCombinedShape() {
    }

    @Test
    public void containsElement() {
    }

    @Test
    public void removeElement() {
    }
}