package at.ac.univie.se2ws2020team0310.ske_app.model.draw;

import android.graphics.Canvas;
import at.ac.univie.se2ws2020team0310.ske_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.ske_app.model.graphicalElements.Quadrangle;

public class DrawQuadrangleStrategy implements DrawStrategy {
    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        canvas.drawRect(graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getxPosition() + ((Quadrangle) graphicalElement).getLength(), graphicalElement.getyPosition() + ((Quadrangle) graphicalElement).getHeight(), graphicalElement.getObjectPaint());
    }
}
