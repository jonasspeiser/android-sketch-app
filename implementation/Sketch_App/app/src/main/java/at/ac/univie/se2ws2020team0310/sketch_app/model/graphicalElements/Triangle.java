package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Triangle extends GraphicalElement {

    // Constructor
    public Triangle(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    public Triangle(Triangle copy) {
        super(copy);
    }

//Other methods

    public boolean isWithinElement(float x, float y) {

        // Coordinates of point A (Bottom Left)
        float xBottomLeft = this.xPosition - this.getSize() / 2;
        float yBottomLeft = this.yPosition + this.getSize() / 2;

        // Coordinates of point B (Top)
        float xTop = this.xPosition;
        float yTop = this.yPosition - this.getSize() / 2;

        // Coordinates of point C (Bottom Right)
        float xBottomRight = this.xPosition + this.getSize() / 2;
        float yBottomRight = this.yPosition + this.getSize() / 2;

        //Based on: https://github.com/SebLague/Gamedev-Maths/blob/master/PointInTriangle.cs
        //0.0001 dummy needed, so there is no NaN Error
        double HeightDifferenceBottom = yBottomRight - yBottomLeft + 0.0001;
        double LengthsBottomEdge = xBottomRight - xBottomLeft;
        double TriangleHeight = yTop - yBottomLeft;
        double HeightDifferenceTouch = y - yBottomLeft;

        double WeightOfVector1 =
                (xBottomLeft * HeightDifferenceBottom + HeightDifferenceTouch * LengthsBottomEdge
                        - x * HeightDifferenceBottom) /
                        (TriangleHeight * LengthsBottomEdge
                                - (xTop - xBottomLeft) * HeightDifferenceBottom);
        double WeightOfVector2 =
                (HeightDifferenceTouch - WeightOfVector1 * TriangleHeight) / HeightDifferenceBottom;

        return WeightOfVector1 >= 0 && WeightOfVector2 >= 0
                && (WeightOfVector1 + WeightOfVector2) <= 1;
    }

    @Override
    public String getName() {
        return "Triangle";
    }

    @Override
    public GraphicalElement copy() {
        return new Triangle(this);
    }
}
