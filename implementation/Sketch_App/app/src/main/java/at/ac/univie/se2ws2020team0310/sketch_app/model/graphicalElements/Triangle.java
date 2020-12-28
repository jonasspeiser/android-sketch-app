package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTriangleStrategy;

public class Triangle extends GraphicalElement {

    public Triangle(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    public float area(float x1, float y1, float x2, float y2, float x3, float y3) {
        return (float) ((x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2)) / 2.0);
    }

    public GraphicalElement graphicalElement;

    public boolean isWithinElement(float x, float y) {
        // TODO: implement method body

        //top
        float xTop = this.xPosition;
        float yTop = this.yPosition;

        // bottom left
        float xBottomLeft = this.xPosition - graphicalElement.getSize()/2;
        float yBottomLeft = this.yPosition + graphicalElement.getSize()/2;

        //bottom right
        float xBottomRight = this.xPosition + graphicalElement.getSize()/2;
        float yBottomRight = this.yPosition + graphicalElement.getSize()/2;

        float A = area(xBottomLeft, yBottomLeft, xTop, yTop, xBottomRight, yBottomRight);

        float A1 = area(x, y, xTop, yTop, xBottomRight, yBottomRight);

        float A2 = area(xBottomLeft, yBottomLeft, x, y, xBottomRight, yBottomRight);

        float A3 = area(xBottomLeft, yBottomLeft, xTop, yTop, x, y);


        if (A == A1+A2+A3) {
            return true;
        } else {
            return false;
        }
    }
}
