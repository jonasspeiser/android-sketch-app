package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Quadrangle;

public class DrawQuadrangleStrategy implements DrawStrategy {

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        canvas.drawRect(graphicalElement.getXPosition(), graphicalElement.getYPosition(), graphicalElement.getXPosition() + ((Quadrangle) graphicalElement).getLength(), graphicalElement.getYPosition() + ((Quadrangle) graphicalElement).getHeight(), graphicalElement.getObjectPaint());
    }
}
