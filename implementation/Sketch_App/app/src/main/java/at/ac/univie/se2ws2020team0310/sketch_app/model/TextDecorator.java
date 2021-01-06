package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;

public class TextDecorator extends GraphicalElementDecorator {


    public TextDecorator(GraphicalElement element) {
        super(element);
    }

    // use isWithinElement method from the decorated element
    @Override
    public boolean isWithinElement(float x, float y) {
        return getElement().isWithinElement(x, y);
    }

    @Override
    protected String getName() {
        return "Text";
    }

    public void onClickUnderlineButton() {
        getElement().getObjectPaint().setUnderlineText(true);
    }


}
