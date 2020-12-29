package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Triangle extends GraphicalElement {

    public Triangle(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    public float area(float x1, float y1, float x2, float y2, float x3, float y3) {
        return (float) ((x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2)) / 2.0);
    }

    public boolean isWithinElement(float x, float y) {
        // TODO: Defensive Programming can be used to check if we are using valid xPos, yPos and graphicalElement attributes

        // Coordinates of point A (Bottom Left)
        float xBottomLeft = this.xPosition - this.getSize()/2;
        float yBottomLeft = this.yPosition + this.getSize()/2;

        // Coordinates of point B (Top)
        float xTop = this.xPosition;
        float yTop = this.yPosition;

        // Coordinates of point C (Bottom Right)
        float xBottomRight = this.xPosition + this.getSize()/2;
        float yBottomRight = this.yPosition + this.getSize()/2;

        // Area of the triangle ABC (Bottom Left, Top, Bottom Right)
        float A = area(xBottomLeft, yBottomLeft, xTop, yTop, xBottomRight, yBottomRight);

        // Area of PBC (choosen Point, Top and Bottom Right)
        float A1 = area(x, y, xTop, yTop, xBottomRight, yBottomRight);

        // Area of PAC (choosen Point, Bottom Left and Bottom Right)
        float A2 = area(xBottomLeft, yBottomLeft, x, y, xBottomRight, yBottomRight);

        // Area of PAB (choosen Point, Bottom Left and Top)
        float A3 = area(xBottomLeft, yBottomLeft, xTop, yTop, x, y);

        if (A == A1+A2+A3) {
            // Means that coordinates are within triangle
            return true;
        } else {
            return false;
        }
    }
}
