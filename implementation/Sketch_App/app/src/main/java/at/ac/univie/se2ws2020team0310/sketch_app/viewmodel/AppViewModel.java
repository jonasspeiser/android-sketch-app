package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Layer;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class AppViewModel extends ViewModel {

// Attributes

    private Layer sketch;

// Constructors

    public AppViewModel() {
        this.sketch = new Layer();
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

    public GraphicalElement getLastElement() { // TODO: Diese Methode komplett von hier entfernen
        return sketch.getLastElement();
    }

    public void changeCoordinates(float x, float y) {
        sketch.changeCoordinates(x, y);
    }

    public void clear() {
        sketch.clear();
    }

    public void selectLine() {
        sketch.selectLine();
    }

    public void selectCircle() {
           sketch.selectCircle();
    }

    public void selectQuadrangle() {
            sketch.selectQuadrangle();
    }

    public void selectTriangle() {
           sketch.selectTriangle();
    }

    public void selectText() {
            sketch.selectText();
    }

}
