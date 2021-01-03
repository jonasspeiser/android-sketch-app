package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;


import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

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
        return getObjectPath();
    }

    @Override
    public boolean isFreehand() {
        return true;
    }

    public boolean isWithinElement(float x, float y) {
        Path tempPath = new Path(); // Create temp Path
        tempPath.moveTo(x,y); // Move cursor to point
        // create rectangle
        RectF rectangle = new RectF(x - 3, y - 3, x + 3, y + 1);
        tempPath.addRect(rectangle, Path.Direction.CW); // add rect to temp path
        tempPath.op(getObjectPath(), Path.Op.DIFFERENCE); // get difference with our PathToCheck
        if (tempPath.isEmpty()) // if out path cover temp path we get empty path in result
        {
            Log.d("Freehand", "Path contains this point");
            return true;
        }

        Log.d("Freehand", "Path doesn't contain this point");
        return false;
    }

    @Override
    protected String getName() {
        return "Freehand Drawing";
    }
}



