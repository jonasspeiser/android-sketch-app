package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.graphics.Color;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.ViewModel;

import at.ac.univie.se2ws2020team0310.sketch_app.BR;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.TextDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;

public class MainViewModel extends BaseObservable {

// Attributes

    private Sketch sketch;

    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

    private boolean editModeOn;

    private TextDecorator textDecorator;    // TODO initialize it in Constructor, add getter/setter

// Constructor
    public MainViewModel() {
        this.sketch = Sketch.getSketch();
        this.selectedColor = Color.BLACK;
        this.selectedSize = 150;
        this.selectedStrokeWidth = 15;
        this.editModeOn = false;
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

    @Bindable
    public void setSelectedStrokeWidth(float selectedStrokeWidth) {
        this.selectedStrokeWidth = selectedStrokeWidth;
        notifyPropertyChanged(BR.selectedStrokeWidth);
    }

// Other Methods

    public void selectLayer(int layerNumber) {
        sketch.setSelectedLayer(layerNumber);
    }

    public void setLayerVisibility(int layerNumber, boolean isVisible) {
        sketch.setLayerVisibility(layerNumber, isVisible);
    }

    public boolean layerIsEmpty() {return sketch.layerIsEmpty();}

    public void buttonClick() {
        textDecorator.buttonClick();    // textDecorator is not initialized, hence the app crashes here
    }


    public void selectGraphicalElement(EGraphicalElementType type) {
        sketch.selectGraphicalElement(type, this.selectedColor, this.selectedSize, this.selectedStrokeWidth);
    }

    public void toggleEditMode(){
        this.editModeOn = !this.editModeOn;
        sketch.setEditModeTurnedOn(this.editModeOn);
    }
}

