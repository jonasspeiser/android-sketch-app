package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Circle extends GraphicalElement {

// Attributes
    private float radius;

// Constructor
    public Circle(DrawStrategy drawStrategy) {
        super(drawStrategy);

    }

// Getters and Setters

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void setSize(float size) {
        super.setSize(size);
        setRadius(size / 2);
    }

    public boolean isWithinElement(float x, float y) {
        double dx = Math.pow(x - this.xPosition, 2);
        double dy = Math.pow(y - this.yPosition, 2);

        if (dx + dy < Math.pow(this.radius, 2)) {
            // means that coordinates are within circle
            return true;
        } else {
            return false;
        }
    }
}
