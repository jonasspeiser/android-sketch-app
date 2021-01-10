package at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException(GraphicalElement element) {
        super(element + " not found");
    }
}
