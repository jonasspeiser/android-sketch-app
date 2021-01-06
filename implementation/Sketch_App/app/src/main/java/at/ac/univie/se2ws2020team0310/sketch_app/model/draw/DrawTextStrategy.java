package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;

public class DrawTextStrategy implements DrawStrategy {
    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        Log.d("DrawTextStrategy", graphicalElement + ": isUnderlineText? " + graphicalElement.getObjectPaint().isUnderlineText());
        canvas.drawText(((Text) graphicalElement).getUserText(), graphicalElement.getxPosition(), graphicalElement.getyPosition(), graphicalElement.getObjectPaint());
    }
}
