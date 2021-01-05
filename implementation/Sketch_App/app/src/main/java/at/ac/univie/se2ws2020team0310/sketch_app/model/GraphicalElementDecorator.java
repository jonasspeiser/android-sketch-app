package at.ac.univie.se2ws2020team0310.sketch_app.model;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;

public abstract class GraphicalElementDecorator extends Text {

    public GraphicalElementDecorator(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }
}
