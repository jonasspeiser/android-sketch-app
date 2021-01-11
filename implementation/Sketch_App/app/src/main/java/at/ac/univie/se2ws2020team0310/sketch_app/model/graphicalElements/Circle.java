package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Circle extends GraphicalElement {

// Attributes
    private float radius;

// Constructor
    public Circle(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    /**
     * Copy Constructor for Circle
     * @param copy  the Circle to copy from
     */
    public Circle(Circle copy) {
        super(copy);
        setRadius(copy.radius);
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

    // Other Methods

    public boolean isWithinElement(float x, float y) {
        double dx = Math.pow(x - this.xPosition, 2);
        double dy = Math.pow(y - this.yPosition, 2);

        if (dx + dy < Math.pow(this.radius, 2)) {
            // Means that coordinates are within circle
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return "Circle";
    }

    @Override
    public GraphicalElement copy() {
        return new Circle(this);
    }
}
