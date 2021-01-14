package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

/**
 * Interface implementing the Strategy Pattern to draw different Graphical Elements
 */
public interface DrawStrategy {

    Paint initializePaint(GraphicalElement graphicalElement);

    void draw(Canvas canvas, GraphicalElement graphicalElement);
}
