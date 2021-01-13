package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.IDrawStrategy;

public class Line extends GraphicalElement {

// Attributes
    private float startX, startY;

    public Line(IDrawStrategy drawStrategy) {
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

        double startXAsDouble = startX;
        double startYAsDouble = startY;
        double xPositionAsDouble = xPosition;
        double yPositionAsDouble = yPosition;

        //Check for collinearity
        int distanceStartNew = (int) Math.hypot(startXAsDouble - x, startYAsDouble - y);
        int distanceNewEnd = (int) Math.hypot(x - xPositionAsDouble, y - yPositionAsDouble);
        int distanceStartEnd = (int) Math.hypot(startXAsDouble - xPositionAsDouble, startYAsDouble - yPositionAsDouble);

        return distanceStartNew + distanceNewEnd == distanceStartEnd;
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
