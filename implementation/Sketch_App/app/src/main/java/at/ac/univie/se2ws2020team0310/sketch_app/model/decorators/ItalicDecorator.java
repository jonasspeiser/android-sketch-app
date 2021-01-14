package at.ac.univie.se2ws2020team0310.sketch_app.model.decorators;

import android.graphics.Paint;
import android.graphics.Typeface;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.IDrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class ItalicDecorator extends DrawStrategyDecorator {

    public ItalicDecorator(IDrawStrategy drawStrategy) {
        super(drawStrategy);
    }

    @Override
    public Paint initializePaint(GraphicalElement graphicalElement) {
        Paint mPaint = getOldStrategy().initializePaint(graphicalElement);

        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        return mPaint;    }
}
