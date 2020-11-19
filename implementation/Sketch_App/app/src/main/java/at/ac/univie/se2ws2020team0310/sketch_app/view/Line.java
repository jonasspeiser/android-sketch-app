package at.ac.univie.se2ws2020team0310.sketch_app.view;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public class Line extends GraphicalElement {

    // Attributes
    private float startX, startY;

    public Line(IDrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    // Getters and Setters
    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

// Polymorphism with setters of Superclass
    // if the startpoint is not defined, set position as startpoint
    // else set position as endpoint

    public void setxPosition(float xPosition) {
        if (getStartX() == 0) {
            setStartX(xPosition);
        } else {
            super.setxPosition(xPosition);
        }
    }

    public void setyPosition(float yPosition) {
        if (getStartY() == 0) {
            setStartY(yPosition);
        } else {
            super.setyPosition(yPosition);
        }
    }

}
