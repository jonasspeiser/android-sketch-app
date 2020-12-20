package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawStrategy;

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
        } else {
            super.setCoordinates(x, y);
        }
    }

}
