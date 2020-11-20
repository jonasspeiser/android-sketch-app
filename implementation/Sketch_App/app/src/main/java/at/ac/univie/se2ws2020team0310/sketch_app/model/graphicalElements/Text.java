package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public class Text extends GraphicalElement {

    private String userText;

    public Text(IDrawStrategy drawStrategy) {
        super(drawStrategy);
        userText = "";
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    public void setSize(float textsize) { // Overrides super.setSize(float size)
        this.getObjectPaint().setTextSize(textsize);
    }

}
