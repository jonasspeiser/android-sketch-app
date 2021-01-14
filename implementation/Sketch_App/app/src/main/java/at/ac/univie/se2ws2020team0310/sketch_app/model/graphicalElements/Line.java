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
        if (getStartX() == 0 && getStartY() == 0) {
            setStartCoordinates(x, y);
            setCoordinates(x, y);
        } else {
            super.setCoordinates(x, y);
        }
    }
//Other methods
// TODO: Problem: changeCoordinates wird aktuell schon bei der Erstellung getriggert. -> Kann ich in changeCoordinates irgendwie den Unterschied erkennen?

    @Override
    public void changeCoordinates(float x, float y, float lastTouchX, float lastTouchY) throws AppException {
        // TODO: implement correct movement of line
        float touchX = lastTouchX;
        float touchY = lastTouchY;

        // calculate the length
        float lengthX = this.xPosition - this.startX;
        float lengthY = this.yPosition - this.startY;

        // set new startcoordinates for the line
        this.startX = touchX;
        this.startY = touchY;

        // set new endcoordinates by adding the length
        this.xPosition = this.startX + lengthX;
        this.yPosition = this.startY + lengthY;
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
