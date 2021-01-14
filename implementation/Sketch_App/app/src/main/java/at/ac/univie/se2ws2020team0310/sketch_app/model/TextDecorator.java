package at.ac.univie.se2ws2020team0310.sketch_app.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class TextDecorator extends GraphicalElementDecorator {

    Boolean italic;
    Boolean bold;
    //Boolean underline = false;


    /**
     * Constructor of a TextDecorator for a different GraphicalElement
     * @param drawStrategy   the element to decorate
     */
    public TextDecorator (DrawStrategy drawStrategy) {
        super(drawStrategy);
    }


    //   /**
    //    * Copy Constructor for a TextDecorator with a copy of the decorated GraphicalElement
    //    * The decorated element has also to be copied, in order to keep the original decorated element intact
    //    * @param copy  the decorator to copy from
    //    */

    /*
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
    */

    public void onClickItalicButton() {
        italic = true;
    }
        /*
        getElement().getObjectPaint().setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

         */


    public void onClickBoldButton() {
        bold = true;
        /*
        getElement().getObjectPaint().setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

         */
    }

    /*public void onClickUnderlineButton() {
        underline = true;

        getElement().getObjectPaint().setUnderlineText(true);

    }*/



    @Override
    public Paint initializePaint(GraphicalElement graphicalElement) {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(graphicalElement.getStrokeWidth());
        mPaint.setColor(graphicalElement.getColor());
        mPaint.setTextSize(graphicalElement.getSize());

        if (italic = true) {
            mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }
        else if (bold == true) {
            mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
        return mPaint;
    }

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {

    }
}