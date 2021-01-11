package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Paint;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Text extends GraphicalElement {

// Attributes
    private String userText;

// Constructor
    public Text(DrawStrategy drawStrategy) {
        super(drawStrategy);
        userText = "";
    }

    public Text(Text copy) {
        super(copy);
        setUserText(copy.getUserText());
    }

// Getters and Setters

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

// Other Methods

    public boolean isWithinElement(float x, float y) {

        Paint mPaint = new Paint();
        mPaint.setTextSize(getSize());

        float textlength = mPaint.measureText(userText);
        float textsize = getSize();

        float xTopLeft = this.xPosition - textlength;
        float yTopLeft = this.yPosition - textsize;

        float xBottomRight = this.xPosition + textlength;
        float yBottomRight = this.yPosition;

        if (x >= xTopLeft && x <= xBottomRight && y >= yTopLeft && y <= yBottomRight)
            return true;
        else
            return false;
    }

    @Override
    public String getName() {
        return "Text";
    }

    @Override
    public GraphicalElement copy() {
        return new Text(this);
    }
}

