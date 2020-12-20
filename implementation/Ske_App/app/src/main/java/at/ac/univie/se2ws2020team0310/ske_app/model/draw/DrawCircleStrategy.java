package at.ac.univie.se2ws2020team0310.ske_app.model.draw;

import android.graphics.Canvas;
import at.ac.univie.se2ws2020team0310.ske_app.model.graphicalElements.GraphicalElement;

public class DrawCircleStrategy implements DrawStrategy {

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        canvas.drawCircle(graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getShapeSize(), graphicalElement.getObjectPaint());
    }
}
