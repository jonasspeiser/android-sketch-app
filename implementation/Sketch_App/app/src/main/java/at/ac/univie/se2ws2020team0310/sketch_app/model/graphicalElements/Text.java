package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Rect;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawStrategy;

public class Text extends GraphicalElement {

// Attributes
    private String userText;

// Constructor
    public Text(DrawStrategy drawStrategy) {
        super(drawStrategy);
        userText = "";
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
        getObjectPaint().setTextSize(textSize);
    }
    
    public boolean isWithinElement(float x, float y) {
        // TODO: implement method body
        /*
        Rect bounds = new Rect();

        getObjectPaint().getTextBounds(userText, 0, userText.length(), bounds);
        float width = bounds.width();
        float height = bounds.height();

        // coordinates of top left
        float xTopLeft = this.xPosition;
        float yTopLeft = this.yPosition;

        //coordinates of bottom right
        float xBottomRight = this.xPosition + width;
        float yBottomRight = this.yPosition + height;

        if (x >= xTopLeft && x <= xBottomRight && y >= yTopLeft && y <= yBottomRight) {
            // means that coordinates are within circle
            return true;
        } else {
     */
            return false;
        }

    @Override
    protected String getName() {
        return "Text";
    }
}

