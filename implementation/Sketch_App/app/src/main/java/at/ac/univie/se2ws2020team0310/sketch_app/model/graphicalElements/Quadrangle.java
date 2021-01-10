package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Quadrangle extends GraphicalElement {

// Attributes
    private float length;
    private float height;

// Constructor
    public Quadrangle(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    public Quadrangle(Quadrangle copy) {
        super(copy);
        setHeight(copy.height);
        setLength(copy.length);
    }

// Getters and Setters

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setSize(float size) {
        super.setSize(size);
        setLength(size);
        setHeight(size);
    }

// Other Methods

    public boolean isWithinElement(float x, float y) {
        // Coordinates of point A (Top Left)
        float xTopLeft = this.xPosition;
        float yTopLeft = this.yPosition;

        // Coordinates of point D (Bottom Right)
        float xBottomRight = this.xPosition + this.length;
        float yBottomRight = this.yPosition + this.height;

        if (x >= xTopLeft && x <= xBottomRight && y >= yTopLeft && y <= yBottomRight)
            // Means that coordinates are within quadrangle
            return true;
        else
            return false;
    }

    @Override
    public String getName() {
        return "Quadrangle";
    }

    @Override
    public GraphicalElement copy() {
        return new Quadrangle(this);
    }
}
