package at.ac.univie.se2ws2020team0310.ske_app.model.graphicalElements;

import android.graphics.Path;
import at.ac.univie.se2ws2020team0310.ske_app.model.draw.DrawStrategy;

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

}
