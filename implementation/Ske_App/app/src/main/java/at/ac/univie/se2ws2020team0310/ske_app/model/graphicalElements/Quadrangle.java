package at.ac.univie.se2ws2020team0310.ske_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.ske_app.model.draw.DrawStrategy;

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

}
