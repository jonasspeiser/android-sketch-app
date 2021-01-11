package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

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
        setUserText(copy.userText);
        /*
        setTextSize(copy.objectPaint.getTextSize());

         */
    }

// Getters and Setters

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    @Override
    public void setSize(float size) {
        super.setSize(size);
        setTextSize(size);
    }

// Other Methods

    public void setTextSize(float textSize) {
        /*getObjectPaint().setTextSize(textSize);

         */
    }
    
    public boolean isWithinElement(float x, float y) {
/*
        float textlength = getObjectPaint().measureText(userText);
        float textsize = getObjectPaint().getTextSize();

        float xTopLeft = this.xPosition - textlength;
        float yTopLeft = this.yPosition - textsize;

        float xBottomRight = this.xPosition + textlength;
        float yBottomRight = this.yPosition;

        if (x >= xTopLeft && x <= xBottomRight && y >= yTopLeft && y <= yBottomRight)
            return true;
        else

 */
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

