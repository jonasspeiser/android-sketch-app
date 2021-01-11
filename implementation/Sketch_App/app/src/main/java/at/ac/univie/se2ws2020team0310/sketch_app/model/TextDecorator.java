package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Typeface;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

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

    public void onClickItalicButton() {
        /*
        getElement().getObjectPaint().setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

         */
    }

    public void onClickBoldButton() {
        /*
        getElement().getObjectPaint().setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

         */
    }

    public void onClickUnderlineButton() {
        /*
        getElement().getObjectPaint().setUnderlineText(true);

         */
    }


}
