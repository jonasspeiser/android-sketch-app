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
        return false;
    }

}
