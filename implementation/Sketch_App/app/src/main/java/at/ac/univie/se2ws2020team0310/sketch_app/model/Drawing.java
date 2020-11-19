package at.ac.univie.se2ws2020team0310.sketch_app.model;


import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public class Drawing extends GraphicalElement {


    //Attribute

    private Path objectPath;

    public Drawing(IDrawStrategy drawStrategy) {
        super(drawStrategy);
    }


//Methoden



    public Path getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(Path objectPath) {
        this.objectPath = objectPath;
    }




}



