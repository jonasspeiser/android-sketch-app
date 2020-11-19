package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public class Triangle extends GraphicalElement {
    public Triangle(IDrawStrategy drawStrategy) {
        super(drawStrategy);
    }
}
