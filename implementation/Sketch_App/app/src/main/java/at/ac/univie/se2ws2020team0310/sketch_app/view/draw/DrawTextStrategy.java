package at.ac.univie.se2ws2020team0310.sketch_app.view.draw;

import android.graphics.Canvas;

import at.ac.univie.se2ws2020team0310.sketch_app.view.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.view.Text;

public class DrawTextStrategy implements IDrawStrategy{
    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        canvas.drawText(((Text) graphicalElement).getTextinput(), graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
    }
}
