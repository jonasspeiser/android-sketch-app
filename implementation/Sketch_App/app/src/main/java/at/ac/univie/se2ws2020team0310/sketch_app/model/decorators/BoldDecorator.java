package at.ac.univie.se2ws2020team0310.sketch_app.model.decorators;

import android.graphics.Paint;
import android.graphics.Typeface;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.IDrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class BoldDecorator extends DrawStrategyDecorator {


    /**
     * Constructor of a BoldDecorator for a different DrawStrategy
     * @param drawStrategy   the drawStrategy to decorate
     */
    public BoldDecorator(IDrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    @Override
    public Paint initializePaint(GraphicalElement graphicalElement) {
        Paint mPaint = getOldStrategy().initializePaint(graphicalElement);

        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        return mPaint;
    }
/*

    /**
     * Copy Constructor for a TextDecorator with a copy of the decorated GraphicalElement
     * The decorated element has also to be copied, in order to keep the original decorated element intact
     * @param copy  the decorator to copy from
     */
    /*
    public BoldDecorator(BoldDecorator copy) {
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
        return new BoldDecorator(this);
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
    */


}
