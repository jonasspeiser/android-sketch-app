package at.ac.univie.se2ws2020team0310.sketch_app.model.decorators;

import android.graphics.Canvas;
import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.IDrawStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;

public abstract class DrawStrategyDecorator implements IDrawStrategy {

    private IDrawStrategy oldStrategy;

    public DrawStrategyDecorator(IDrawStrategy drawStrategy) {
        this.oldStrategy = drawStrategy;
    }

    public IDrawStrategy getOldStrategy() {
        return oldStrategy;
    }

    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        Paint mPaint = initializePaint(graphicalElement);

        canvas.drawText(((Text) graphicalElement).getUserText(), graphicalElement.getXPosition(),
                graphicalElement.getYPosition(), mPaint);
    }

    public abstract Paint initializePaint(GraphicalElement graphicalElement);

}
