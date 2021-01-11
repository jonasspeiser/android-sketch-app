package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Freehand;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
//TODO: Überarbeiten
public class DrawFreehandStrategy implements DrawStrategy {
    @Override
    public Paint initializePaint() {
        return null;
    }

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        /*Path path = ((Freehand) graphicalElement).getObjectPath();   // cast
        canvas.drawPath(path, graphicalElement.getObjectPaint()); //drawing


         */
    }
}
