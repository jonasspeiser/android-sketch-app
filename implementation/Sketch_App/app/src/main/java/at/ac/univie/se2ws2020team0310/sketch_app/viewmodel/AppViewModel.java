package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class AppViewModel extends ViewModel {
    // Diese Klasse können wir auch verwenden, um Layer darzustellen (dann evtl umbenennen)

// Attributes

    private GraphicalElement selectedGraphicalElement;

    private final List<GraphicalElement> drawnElements;

// Constructors

    public AppViewModel() {
        this.drawnElements = new ArrayList<>();
    }

// Getters and Setters

    public GraphicalElement getSelectedGraphicalElement() {
        return selectedGraphicalElement;
    }

    public void setSelectedGraphicalElement(GraphicalElement selectedGraphicalElement) {
        this.selectedGraphicalElement = selectedGraphicalElement;
    }

    public List<GraphicalElement> getDrawnElements() {
        return drawnElements;
    }

// Other Methods

    public void storeElement() {
        drawnElements.add(selectedGraphicalElement);
    }

    public GraphicalElement getLastElement() {
        return drawnElements.get(drawnElements.size() - 1);
    }

    public void clear() {
        drawnElements.clear();
    }

    public void selectLine() {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.LINE));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectCircle() {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.CIRCLE));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public void selectQuadrangle() {

        // use a Factory to create the Quadrangle as a GraphicalElement
        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.QUADRANGLE));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    public void selectTriangle() {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.TRIANGLE));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }

    }

    public void selectText() {

        try {
            this.setSelectedGraphicalElement(GraphicalElementFactory.createElement(EGraphicalElementType.TEXT_FIELD));
        } catch (AppException e) {
            Log.e("CanvasView", e.getMessage());
        }
    }

    /*
currently selected Element

create layer
    new ElementStorage

selectedLayer.getlastElement()


 */
}
