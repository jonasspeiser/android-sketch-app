package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;


import android.graphics.Path;
import android.graphics.RectF;

import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Coordinate;
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Freehand extends GraphicalElement {

// Attributes

    private List<Coordinate> objectPath;
    public Freehand(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    public Freehand(Freehand copy) {
        super(copy);
        setObjectPath(new Path(copy.objectPath));
    }

// Methods
    public List<Coordinate> getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(List<Coordinate> objectPath) {
        this.objectPath = objectPath;
    }

    @Override
    public List<Coordinate> getPath() {
        return getObjectPath();
    }

    @Override
    public boolean isFreehand() {
        return true;
    }

    public boolean isWithinElement(float x, float y) {
        Path tempPath = new Path(); // Create temp Path
        tempPath.moveTo(x,y); // Move cursor to point
        // create rectangle
        RectF rectangle = new RectF(x - 3, y - 3, x + 3, y + 1);
        tempPath.addRect(rectangle, Path.Direction.CW); // add rect to temp path
        tempPath.op(getObjectPath(), Path.Op.DIFFERENCE); // get difference with our PathToCheck
        return tempPath.isEmpty(); // if our path covers temp path, we get empty path in result
    }

    @Override
    public String getName() {
        return "Freehand Drawing";
    }

    @Override
    public GraphicalElement copy() {
        return new Freehand(this);
    }

    /**
     * Update current coordinates and Path, in order to move the Freehand drawing to the new position
     * @param x the new coordinate x
     * @param y the new coordinate y
     * @param lastTouchX    the previous coordinate x (used for Freehand drawing)
     * @param lastTouchY    the previous coordinate y (used for Freehand drawing)
     */
    @Override
    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) throws AppException {
        super.changeCoordinates(x, y, lastTouchX, lastTouchY);
        if (getPath() != null && lastTouchX > 0 && lastTouchY > 0) {
            getPath().offset(x - lastTouchX, y - lastTouchY);
        }
    }
}



