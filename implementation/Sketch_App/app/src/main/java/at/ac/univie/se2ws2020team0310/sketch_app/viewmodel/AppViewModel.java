package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.graphics.Paint;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Layer;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
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

    public void changeColor(int color) {
        sketch.changeColor(color);
    }

    public void changeStrokeWidth(int strokewidth) {
        sketch.changeStrokeWidth((float) strokewidth);
    }

    public void changeTextSize(int textsize) {
        sketch.changeTextSize(textsize);
    }

    public void changeCoordinates(float x, float y) {
        sketch.changeCoordinates(x, y);
    }

    public void clear() {
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


    public void onTouchDown(float x, float y) {
        if (this.isWithinElement(x, y)) {
            this.moveElement = true;
        }
    }

    public void onTouchMove(float x, float y) {
        if (this.moveElement) {
            this.changeCoordinates(x, y);
        }
    }

    public void onTouchUp() {
        this.moveElement = false;
    }

}
