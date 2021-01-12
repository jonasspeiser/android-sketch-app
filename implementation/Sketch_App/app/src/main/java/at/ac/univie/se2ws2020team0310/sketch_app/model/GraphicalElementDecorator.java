package at.ac.univie.se2ws2020team0310.sketch_app.model;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public abstract class GraphicalElementDecorator implements DrawStrategy {

    // der Decorator enthält ein GraphicalElement
    private DrawStrategy drawStrategy;

    public GraphicalElementDecorator(DrawStrategy drawStrategy) {
        super();
        this.drawStrategy = drawStrategy;
    }

}

