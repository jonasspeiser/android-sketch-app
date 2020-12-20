package at.ac.univie.se2ws2020team0310.sketch_app.view.draw;

import android.graphics.Canvas;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public interface DrawStrategy {
    void draw(Canvas canvas, GraphicalElement graphicalElement);
}
