package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;
import static java.lang.Math.abs;

public class Line extends GraphicalElement {

    // Attributes
    private float startX, startY;

    public Line(DrawStrategy drawStrategy) {
        super(drawStrategy);
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
    public void setCoordinates(float x, float y) {
        if (getStartX() == 0 & getStartY() == 0) {
            setStartCoordinates(x, y);
            setCoordinates(x, y);
        } else {
            super.setCoordinates(x, y);
        }
    }

    //Other methods

    @Override
    public void changeCoordinates(float x, float y) {
        // TODO: implement correct movement of line
        if (getStartX() == 0 & getStartY() == 0) {
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
            // Means that coordinates are within quadrangle
        }else {
            return false;
        }
    }
}
