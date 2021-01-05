package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Triangle extends GraphicalElement {

// Constructor
    public Triangle(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

//Other methods

    public boolean isWithinElement(float x, float y) {
        // TODO: Methode sollte vereinfacht werden

        // Coordinates of point A (Bottom Left)
        float xBottomLeft = this.xPosition - this.getSize() / 2;
        float yBottomLeft = this.yPosition + this.getSize() / 2;

        // Coordinates of point B (Top)
        float xTop = this.xPosition;
        float yTop = this.yPosition - this.getSize() / 2;

        // Coordinates of point C (Bottom Right)
        float xBottomRight = this.xPosition + this.getSize() / 2;
        float yBottomRight = this.yPosition + this.getSize() / 2;

        //0.0001 dummy needed, so there is no NaN Error
        double s1 = yBottomRight - yBottomLeft  + 0.0001;
        double s2 = xBottomRight - xBottomLeft;
        double s3 = yTop - yBottomLeft;
        double s4 = y - yBottomLeft;

        double w1 = (xBottomLeft * s1 + s4 * s2 - x * s1) / (s3 * s2 - (xTop - xBottomLeft) * s1);
        double w2 = (s4 - w1 * s3) / s1;

        if (w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1)
            return true;
            // Means that coordinates are within triangle
        else
            return false;
    }

    @Override
    protected String getName() {
        return "Triangle";
    }
}
