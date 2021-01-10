package at.ac.univie.se2ws2020team0310.sketch_app.model;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public abstract class GraphicalElementDecorator extends GraphicalElement {

    // der Decorator enthält ein GraphicalElement
    private GraphicalElement element;

    public GraphicalElementDecorator(GraphicalElement element) {
        super(element.getDrawStrategy());
        this.element = element;
    }

    public GraphicalElement getElement() {
        return element;
    }

}
