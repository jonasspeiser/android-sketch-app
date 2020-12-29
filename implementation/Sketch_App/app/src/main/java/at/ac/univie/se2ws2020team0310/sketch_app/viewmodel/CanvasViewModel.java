package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.graphics.Path;

import androidx.lifecycle.ViewModel;

import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class  CanvasViewModel extends ViewModel {

// Attributes

    private final Sketch sketch;
    private boolean moveElement;
    private Path path;

// Constructors

    public CanvasViewModel() {
        this.sketch = Sketch.getSketch();
        this.moveElement = false;
    }

// Other Methods

    public GraphicalElement getSelectedGraphicalElement() {
        return sketch.getSelectedGraphicalElement();
    }

    public List<GraphicalElement> getDrawnElements() {
        return sketch.getDrawnElements();
    }

    public void storeElement() {
        sketch.storeElement();
    }

    public void changeElementColor(int color) {
        sketch.changeColor(color);
    }

    public void changeElementStrokeWidth(int strokewidth) {
        sketch.changeStrokeWidth((float) strokewidth);
    }

    public void changeElementSize(int size) {
        sketch.changeSize(size);
    }

    public void setElementCoordinates(float x, float y) {
        sketch.setCoordinates(x,y);
    }

    /** write given coordinates (x, y) to the last selected graphical element */
    public void changeElementCoordinates(float x, float y) {
        sketch.changeCoordinates(x, y);
    }

    public void deleteElement(){sketch.deleteElement();}

    public void clearSketch() {
        sketch.clear();
    }

    public void resetSelection() {
        sketch.resetSelection();
    }

    public boolean isWithinElement(float x, float y) {
        // TODO: Bessere Namensalternativen (weil die Methode nicht nur true zurückgibt, sondern das Element auch editierbar macht)
        return sketch.isWithinElement(x, y);
    }

    public boolean elementsToDraw() {
        return this.getDrawnElements() != null;
    }

    public void elementsBehaviourOnTouchDown(float x, float y) {
        // behaviour for drawing a new element
        if (this.getSelectedGraphicalElement() != null) {
            this.storeElement();
            this.setElementCoordinates(x, y);
            this.moveElement = true;
        }
        // behaviour for touching an existing element
        if (this.getSelectedGraphicalElement() == null && this.getDrawnElements() != null) {
            if (this.isWithinElement(x, y)) {
                this.moveElement = true;
            }
        }
    }

    public void elementsBehaviourOnTouchMove(float x, float y) {
        // behaviour for drawing a new element
        if (this.getSelectedGraphicalElement() != null) {
            this.setElementCoordinates(x, y);
        }
        // behaviour for touching an existing element
        if (this.moveElement) {
            this.changeElementCoordinates(x, y);
        }
    }

    public void elementsBehaviourOnTouchUp() {
        this.resetSelection();
        this.moveElement = false;
    }

    public void freehandBehaviourOnTouchDown(float touchX, float touchY) {
        path = null;
        if (getSelectedGraphicalElement() != null){
            path = getSelectedGraphicalElement().getPath();
        }

        if (path != null) {
            path.moveTo(touchX, touchY); // start ist hier
        }
    }

    public void freehandBehaviourOnTouchMove(float touchX, float touchY) {
        if (path != null) {
            path.lineTo(touchX, touchY);
        }
    }

    public void freehandBehaviourOnTouchUp(float touchX, float touchY) {
        if (path != null) {
            path.lineTo(touchX, touchY);
        }
    }
}
