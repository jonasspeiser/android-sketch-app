package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import at.ac.univie.se2ws2020team0310.sketch_app.BR;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.TextDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.iterators.IterableCollection;

public class MainViewModel extends BaseObservable {

// Attributes

    public String imgSaved;

    private static Sketch sketch;

    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

    private boolean editModeOn;

    // Constructor
    public MainViewModel() {
        sketch = Sketch.getSketch();
        this.selectedColor = Color.BLACK;
        this.selectedSize = 150;
        this.selectedStrokeWidth = 15;
        this.editModeOn = false;
    }
// Getters and Setters

    public static void setSketch(Sketch sketch) {
        MainViewModel.sketch = sketch;
    }

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

    public void exportCanvas() {

    }



    public void selectLayer(int layerNumber) {
        sketch.setSelectedLayer(layerNumber);
    }

    public void setLayerVisibility(int layerNumber, boolean isVisible) {
        sketch.setLayerVisibility(layerNumber, isVisible);
    }

    public boolean layerIsEmpty() {return sketch.layerIsEmpty();}


    public void onClickItalicButton() {
        TextDecorator textDecorator = new TextDecorator(sketch.getSelectedGraphicalElement());
        textDecorator.onClickItalicButton();
    }

    public void onClickBoldButton() {
        TextDecorator textDecorator = new TextDecorator(sketch.getSelectedGraphicalElement());
        textDecorator.onClickBoldButton();
    }

    public void onClickUnderlineButton() {
        TextDecorator textDecorator = new TextDecorator(sketch.getSelectedGraphicalElement());
        textDecorator.onClickUnderlineButton();
    }

    public void selectGraphicalElement(EGraphicalElementType type) {
        sketch.selectGraphicalElement(type, this.selectedColor, this.selectedSize, this.selectedStrokeWidth);
    }

    public void toggleEditMode(){
        this.editModeOn = !this.editModeOn;
        sketch.setEditModeTurnedOn(this.editModeOn);
    }

    public boolean isEditModeOn() {
        return editModeOn;
    }

    public void saveSketch(Context context) {
        sketch.saveToFile(context);
    }

    public void loadSketch(Context context) {
        Sketch loadedSketch = Sketch.readFromFile(context);

        // Right now the new Sketch only gets loaded into the viewmodel, not into the model
        //TODO: realize this with an Observer: the viewModels always check which sketch-object is loaded in Sketch class
        setSketch(loadedSketch);
        CanvasViewModel.setSketch(loadedSketch);

        // The selected Layer doesn't get stored properly, therefore we reselect one manually
        // TODO: fix this (e.g. by only storing the selected layernumber in Sketch instead of a whole object
        selectLayer(0);
    }

}

