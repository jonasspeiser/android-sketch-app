package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.graphics.Color;

import androidx.lifecycle.ViewModel;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.TextDecorator;

public class MainViewModel extends ViewModel {

// Attributes

    private Sketch sketch;

    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

    private TextDecorator textDecorator;

// Constructor
    public MainViewModel() {
        this.sketch = Sketch.getSketch();
        this.selectedColor = Color.BLACK;
        this.selectedSize = 150;
        this.selectedStrokeWidth = 15;
    }
// Getters and Setters

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public float getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(float selectedSize) {
        this.selectedSize = selectedSize;
    }

    public float getSelectedStrokeWidth() {
        return selectedStrokeWidth;
    }

    public void setSelectedStrokeWidth(float selectedStrokeWidth) {
        this.selectedStrokeWidth = selectedStrokeWidth;
    }

// Other Methods

    public void selectLine() {
        sketch.selectLine(this.selectedColor, this.selectedSize, this.selectedStrokeWidth);
    }

    public void selectCircle() {
        sketch.selectCircle(this.selectedColor, this.selectedSize, this.selectedStrokeWidth);
    }

    public void selectQuadrangle() {
        sketch.selectQuadrangle(this.selectedColor, this.selectedSize, this.selectedStrokeWidth);
    }

    public void selectTriangle() {
        sketch.selectTriangle(this.selectedColor, this.selectedSize, this.selectedStrokeWidth);
    }

    public void selectText() {
        sketch.selectText(this.selectedColor, this.selectedSize, this.selectedStrokeWidth);
    }

    public boolean layerIsEmpty() {return sketch.layerIsEmpty();}

    public TextDecorator buttonClick() {
        textDecorator.buttonClick();
        return null;
    }


}

