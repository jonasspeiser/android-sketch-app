package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
//TODO: Überarbeiten(?)
/**
 *  Implementation of a Strategy to draw a Combined Shape
 *  Each element included in the Combined Shape will be drawn according to its own Strategy
 */
public class DrawCombinedShapeStrategy implements IDrawStrategy {

    @Override
    public Paint initializePaint(GraphicalElement graphicalElement) {
        return null;
    }

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        CombinedShape combinedShape = (CombinedShape) graphicalElement;
        for (GraphicalElement element : combinedShape.getElements()) {
            element.draw(canvas);
        }
    }
}
