package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Line extends GraphicalElement {

// Attributes
    private float startX, startY;

    public Line(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    public Line(Line copy) {
        super(copy);
        setStartCoordinates(copy.startX, copy.startY);
    }

    // Getters and Setters
    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartCoordinates(float x, float y) {
        this.startX = x;
        this.startY = y;
    }
// Polymorphism with setter of Superclass
    // if the startpoint is not defined, set position as startpoint
    // else set position as endpoint
    @Override
    public void setCoordinates(float x, float y) throws AppException {
            if (getStartX() == 0 & getStartY() == 0) {  // TODO replace & with && (& is a bitwise operator and should only used for this purpose)
                setStartCoordinates(x, y);
                setCoordinates(x, y);
            } else {
                super.setCoordinates(x, y);
            }
        }
//Other methods

        @Override
    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) throws AppException {
            // TODO: implement correct movement of line
            if (getStartX() == 0 & getStartY() == 0) {  // TODO replace & with && (& is a bitwise operator and should only used for this purpose)
                setStartCoordinates(x, y);
                setCoordinates(x, y);
            } else {
                super.setCoordinates(x, y);

        }
    }

    public boolean isWithinElement(float x, float y) {
        double xNewTouchAsDouble = (double) x;
        double yNewTouchAsDouble = (double) y;
        double startXAsDouble = (double) startX;
        double startYAsDouble = (double) startY;
        double xPositionAsDouble = (double) xPosition;
        double yPositionAsDouble = (double) yPosition;

        //Check for colinearity
        int distanceStartNew = (int) Math.hypot(startXAsDouble - xNewTouchAsDouble, startYAsDouble - yNewTouchAsDouble);
        int distanceNewEnd = (int) Math.hypot(xNewTouchAsDouble - xPositionAsDouble, yNewTouchAsDouble - yPositionAsDouble);
        int distanceStartEnd = (int) Math.hypot(startXAsDouble - xPositionAsDouble, startYAsDouble - yPositionAsDouble);

        if (distanceStartNew + distanceNewEnd == distanceStartEnd) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getName() {
        return "Line";
    }

    @Override
    public GraphicalElement copy() {
        return new Line(this);
    }
}
