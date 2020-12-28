package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;


import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Freehand extends GraphicalElement {

// Attributes

    private Path objectPath;
    public Freehand(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }


// Methods

    public Path getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(Path objectPath) {
        this.objectPath = objectPath;
    }

    @Override
    public Path getPath() {
        return objectPath;
    }

    public boolean isWithinElement(float x, float y) {
        // TODO: implement method body
        return false;
    }

}



