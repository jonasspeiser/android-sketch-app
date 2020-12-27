package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.graphics.Paint;

import androidx.lifecycle.ViewModel;

import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class AppViewModel extends ViewModel {

// Attributes

    private final Sketch sketch;
    private boolean moveElement;

// Constructors

    public AppViewModel() {
        this.sketch = new Sketch();
        this.moveElement = false;
    }

// Getters and Setters

    public GraphicalElement getSelectedGraphicalElement() {
        return sketch.getSelectedGraphicalElement();
    }

    public List<GraphicalElement> getDrawnElements() {
        return sketch.getDrawnElements();
    }

// Other Methods

    public void storeElement() {
        sketch.storeElement();
    }

    public boolean layerIsEmpty() {return sketch.layerIsEmpty();}

    public void changeElementColor(int color) {
        sketch.changeColor(color);
    }

    public void changeElementStrokeWidth(int strokewidth) {
        sketch.changeStrokeWidth((float) strokewidth);
    }

    public void changeElementTextSize(int textsize) {
        sketch.changeTextSize(textsize);
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

    public void selectLine(Paint selectedPaint) {
        sketch.selectLine(selectedPaint);
    }

    public void selectCircle(Paint selectedPaint) {
           sketch.selectCircle(selectedPaint);
    }

    public void selectQuadrangle(Paint selectedPaint) {
            sketch.selectQuadrangle(selectedPaint);
    }

    public void selectTriangle(Paint selectedPaint) {
           sketch.selectTriangle(selectedPaint);
    }

    public void selectText(Paint selectedPaint) {
            sketch.selectText(selectedPaint);
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
        if (this.getSelectedGraphicalElement() == null & this.getDrawnElements() != null) {
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

    public void freehandBehaviourOnTouchDown() {
        // TODO
    }

    public void freehandBehaviourOnTouchMove() {
        // TODO
    }

    public void freehandBehaviourOnTouchUp () {
        // TODO
    }

}
