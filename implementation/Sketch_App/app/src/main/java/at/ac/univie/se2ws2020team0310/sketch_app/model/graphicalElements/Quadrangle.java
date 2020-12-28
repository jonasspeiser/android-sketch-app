package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Quadrangle extends GraphicalElement {

// Attributes
    private float length;
    private float height;

    public Quadrangle(DrawStrategy drawStrategy) {
        super(drawStrategy);
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
        //coordinates of point A (top left)
        float xTopLeft = this.xPosition;
        float yTopLeft = this.yPosition;

        //coordinates of point B (top right)
        float xTopRight = this.xPosition + this.length;
        float yTopRight = this.yPosition;

        //coordinates of point C (bottom left)
        float xBottomLeft = this.xPosition;
        float yBottomLeft = this.yPosition + this.height;

        //coordinates of point D (bottom right)
        float xBottomRight = this.xPosition + this.length;
        float yBottomRight = this.yPosition + this.height;

        if (x >= xTopLeft && x <= xBottomRight && y >= yTopLeft && y <= yBottomRight) {
            // means that coordinates are within circle
            return true;
        } else {
            return false;
        }
    }
}
