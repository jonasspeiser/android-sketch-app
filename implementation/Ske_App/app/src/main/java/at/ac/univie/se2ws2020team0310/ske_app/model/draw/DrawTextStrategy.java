package at.ac.univie.se2ws2020team0310.ske_app.model.draw;

import android.graphics.Canvas;
import at.ac.univie.se2ws2020team0310.ske_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.ske_app.model.graphicalElements.Text;

public class DrawTextStrategy implements DrawStrategy {
    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        canvas.drawText(((Text) graphicalElement).getUserText(), graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
    }
}
