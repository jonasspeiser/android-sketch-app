package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public class Text extends GraphicalElement {

    private String textInput;

    public Text(IDrawStrategy drawStrategy) {
        super(drawStrategy);
        textInput = "";
    }

    public String getTextInput() {
        return textInput;
    }

    public void setTextInput(String textInput) {
        this.textInput = textInput;
    }

    public void setSize(float textsize) { // Overrides super.setSize(float size)
        this.getObjectPaint().setTextSize(textsize);
    }

}
