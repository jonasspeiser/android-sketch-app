package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Canvas;

import java.util.Random;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public class Circle extends GraphicalElement {

    public Circle(IDrawStrategy drawStrategy) {
        super(drawStrategy);
    }
}
