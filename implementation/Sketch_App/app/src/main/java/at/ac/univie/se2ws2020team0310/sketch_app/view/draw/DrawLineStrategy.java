package at.ac.univie.se2ws2020team0310.sketch_app.view.draw;

import android.graphics.Canvas;

import at.ac.univie.se2ws2020team0310.sketch_app.model.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Line;

public class DrawLineStrategy implements IDrawStrategy{
    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        canvas.drawLine(((Line) graphicalElement).getStartX(), ((Line) graphicalElement).getStartY(), graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
    }
}
