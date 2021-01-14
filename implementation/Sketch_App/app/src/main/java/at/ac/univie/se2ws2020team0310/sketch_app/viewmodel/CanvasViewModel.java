package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Path;
import androidx.lifecycle.ViewModel;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatterInterfaces.CustomObservable;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatterInterfaces.CustomObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CanvasViewModel extends ViewModel implements CustomObserver, CustomObservable {

// Attributes

    private ArrayList<CustomObserver> observers;
    private static Sketch sketch;
    private boolean isElementSelected;  // renamed from 'moveElement' because in Edit mode a selected Element cannot be moved
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
        sketch.registerObserver(this);
        this.observers = new ArrayList<>();
        this.isElementSelected = false;
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

    public void setElementCoordinates(float x, float y) {
        sketch.setCoordinates(x, y);
    }

    /**
     * Write given coordinates (x, y) to the last selected graphical element
     *
     * @param x          coordinate x
     * @param y          coordinate y
     * @param lastTouchX coordinate x of last touch position
     * @param lastTouchY coordinate y of last touch position
     */
    public void changeElementCoordinates(float x, float y, float lastTouchX, float lastTouchY) {
        sketch.changeCoordinates(x, y, lastTouchX, lastTouchY);
    }

    public void resetSelection() {
        sketch.resetSelection();
    }

    public boolean isWithinElement(float x, float y) throws AppException {
        // TODO: Bessere Namensalternativen (weil die Methode nicht nur true zurückgibt, sondern das Element auch editierbar macht)
        return sketch.isWithinElement(x, y);
    }

    public boolean elementsToDraw() {
        return this.getDrawnElements() != null && !this.getDrawnElements().isEmpty();
    }

    private void elementsBehaviourOnTouchDown(float x, float y) throws AppException {
        // behaviour for drawing a new element
        if (this.getSelectedGraphicalElement() != null) {
            this.storeElement();
            this.setElementCoordinates(x, y);
        }
        // behaviour for touching an existing element
        if (this.getSelectedGraphicalElement() == null && this.getDrawnElements() != null
                && this.isWithinElement(x, y)) {
            this.isElementSelected = true;
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
        if (this.isElementSelected) {
            this.changeElementCoordinates(x, y, this.lastTouchX, this.lastTouchY);
        }
        this.lastTouchX = x;
        this.lastTouchY = y;
    }

    private void elementsBehaviourOnTouchUp() {
        this.resetSelection();
        this.isElementSelected = false;
        this.lastTouchX = -1;
        this.lastTouchY = -1;
    }

    public void onTouchDown(float touchX, float touchY) throws AppException {
        elementsBehaviourOnTouchDown(touchX, touchY);
        freehandBehaviourOnTouchDown(touchX, touchY);
    }

    private void freehandBehaviourOnTouchDown(float touchX, float touchY) {
        path = null;
        if (getSelectedGraphicalElement() != null) {
            path = getSelectedGraphicalElement().getPath();
        }
        if (path != null) {
            path.moveTo(touchX, touchY); // start is here
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

    public boolean export(Context context, String fileFormat, Bitmap drawingCache)
            throws IOException {
        sketch.export(context, fileFormat, drawingCache);
        return true;
    }

    @Override
    public void update() {
        notifyObservers();
    }

    @Override
    public void registerObserver(CustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(CustomObserver observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (CustomObserver observer : observers) {
            observer.update();
        }
    }
}
