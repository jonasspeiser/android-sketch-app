package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.widget.EditText;

import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public class Text extends GraphicalElement {

    private String textinput;

    public Text(IDrawStrategy drawStrategy) {
        super(drawStrategy);
        textinput = "";
    }

    public String getTextinput() {
        return textinput;
    }

    public void setTextinput(String textinput) {
        this.textinput = textinput;
    }

}
