package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import at.ac.univie.se2ws2020team0310.sketch_app.BR;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.TextDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;

public class MainActivityViewModel extends BaseObservable { // TODO: Muss diese Klasse nicht eher extends ViewModel haben?

// Attributes

    public String imgSaved;

    private static Sketch sketch;

    // TODO: Ist es wirklich nötig, diese Attribute in Sketch UND MainActivityViewModel zu haben?
    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

    private boolean editModeOn;

    // Constructor
    public MainActivityViewModel() {
        sketch = Sketch.getSketch();
        this.editModeOn = false;
    }
// Getters and Setters

    public static void setSketch(Sketch sketch) {
        MainActivityViewModel.sketch = sketch;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        sketch.setSelectedColor(selectedColor);
    }

    public float getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(float selectedSize) {
        this.selectedSize = selectedSize;
        sketch.setSelectedSize(selectedSize);
    }

    public float getSelectedStrokeWidth() {
        return selectedStrokeWidth;
    }

    @Bindable
    public void setSelectedStrokeWidth(float selectedStrokeWidth) {     // TODO: Was genau tut das? Wieso gibts das nur bei Strokewidth?
        this.selectedStrokeWidth = selectedStrokeWidth;
        sketch.setSelectedStrokeWidth(selectedStrokeWidth);
        notifyPropertyChanged(BR.selectedStrokeWidth);
    }


// Other Methods

    public void exportCanvas() {

    }

    public void changeElementColor(int color) {
        sketch.changeColor(color);
    }

    public void changeElementStrokeWidth(int strokewidth) {
        sketch.changeStrokeWidth((float) strokewidth);
    }

    public void changeElementSize(int size) {
        sketch.changeSize(size);
    }

    public void deleteElement(){sketch.deleteElement();}

    public void clearSketch() {
        sketch.clear();
    }


    public void selectLayer(int layerNumber) {
        sketch.setSelectedLayerIndex(layerNumber);
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
        sketch.selectGraphicalElement(type);
    }

    //TODO: Ist es wirklich nötig, Attribut EditMode in Sketch UND MainViewModel zu haben?
    public void toggleEditMode(){
        this.editModeOn = !this.editModeOn;
        sketch.setEditModeTurnedOn(this.editModeOn);
    }

    public boolean isEditModeOn() {
        return editModeOn;
    }

    public void saveSketch(Context context) {
        sketch.saveLayersToFile(context);
    }

    public void loadSketch(Context context) {
        sketch.loadLayersFromFile(context);
    }

}

