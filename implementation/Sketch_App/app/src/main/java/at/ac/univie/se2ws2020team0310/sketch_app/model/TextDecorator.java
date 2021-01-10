package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Typeface;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class TextDecorator extends GraphicalElementDecorator {


    /**
     * Constructor of a TextDecorator for a different GraphicalElement
     * @param element   the element to decorate
     */
    public TextDecorator(GraphicalElement element) {
        super(element);
    }

    /**
     * Copy Constructor for a TextDecorator with a copy of the decorated GraphicalElement
     * The decorated element has also to be copied, in order to keep the original decorated element intact
     * @param copy  the decorator to copy from
     */
    public TextDecorator(TextDecorator copy) {
        super(copy.getElement().copy());
    }

    // use isWithinElement method from the decorated element
    @Override
    public boolean isWithinElement(float x, float y) {
        return getElement().isWithinElement(x, y);
    }

    @Override
    public String getName() {
        return "Text";
    }

    @Override
    public GraphicalElement copy() {
        return new TextDecorator(this);
    }

    public void onClickItalicButton() {
        getElement().getObjectPaint().setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
    }

    public void onClickBoldButton() {
        getElement().getObjectPaint().setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    public void onClickUnderlineButton() {
        getElement().getObjectPaint().setUnderlineText(true);
    }
}
