package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;

public class TextDecorator extends GraphicalElementDecorator {

    public TextDecorator(DrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    public void buttonClick() {
        getObjectPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }


}
