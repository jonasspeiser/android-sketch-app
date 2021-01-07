package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Path;

import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class CanvasViewModel extends ViewModel {

// Attributes

    private static Sketch sketch;
    private boolean moveElement;
    private Path path;
    private float lastTouchX;
    private float lastTouchY;

// Getters and Setters

    public static void setSketch(Sketch sketch) {
        CanvasViewModel.sketch = sketch;
    }

// Constructors

    public CanvasViewModel() {
        sketch = Sketch.getSketch();
        this.moveElement = false;
        this.lastTouchX = -1;
        this.lastTouchY = -1;
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
    public void changeElementCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        sketch.changeCoordinates(x, y, lastTouchX, lastTouchY);
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

    private void elementsBehaviourOnTouchDown(float x, float y) {
        // behaviour for drawing a new element
        if (this.getSelectedGraphicalElement() != null) {
            this.storeElement();
            this.setElementCoordinates(x, y);
            this.moveElement = true;
        }
        // behaviour for touching an existing element
        if (this.getSelectedGraphicalElement() == null && this.getDrawnElements() != null
                && this.isWithinElement(x, y)) {
            this.moveElement = true;
        }
        // saving these coordinates as the last point touched
        this.lastTouchX = x;
        this.lastTouchY = y;
    }

    private void elementsBehaviourOnTouchMove(float x, float y) {
        // behaviour for drawing a new element
        if (this.getSelectedGraphicalElement() != null) {
            this.setElementCoordinates(x, y);
        }
        // behaviour for touching an existing element
        if (this.moveElement) {
            this.changeElementCoordinates(x, y, this.lastTouchX, this.lastTouchY);
        }
        this.lastTouchX = x;
        this.lastTouchY = y;
    }

    private void elementsBehaviourOnTouchUp() {
        this.resetSelection();
        this.moveElement = false;
        this.lastTouchX = -1;
        this.lastTouchY = -1;
    }

    public void onTouchDown(float touchX, float touchY) {
        elementsBehaviourOnTouchDown(touchX, touchY);
        freehandBehaviourOnTouchDown(touchX, touchY);
    }

    private void freehandBehaviourOnTouchDown(float touchX, float touchY) {
        path = null;
        if (getSelectedGraphicalElement() != null) {
            path = getSelectedGraphicalElement().getPath();
        }
        if (path != null) {
            path.moveTo(touchX, touchY); // start ist hier
        }
    }

    public void onTouchMove(float touchX, float touchY) {
        if (getSelectedGraphicalElement() != null && getSelectedGraphicalElement().isFreehand()) {
            freehandBehaviourOnTouchMove(touchX, touchY);
        } else {
            elementsBehaviourOnTouchMove(touchX, touchY);
        }
    }

    private void freehandBehaviourOnTouchMove(float touchX, float touchY) {
        if (path != null) {
            path.lineTo(touchX, touchY);
        }
    }

    public void onTouchUp() {
        elementsBehaviourOnTouchUp();
    }

    public void export(Context context,ContentResolver contentResolver,  String fileFormat, Bitmap drawingCache) throws IOException {
        sketch.export(context, contentResolver, fileFormat, drawingCache);
    }

}
