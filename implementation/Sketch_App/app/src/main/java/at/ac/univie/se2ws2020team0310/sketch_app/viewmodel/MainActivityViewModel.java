package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.BR;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.TextDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;

public class MainActivityViewModel extends BaseObservable { // TODO: Muss diese Klasse nicht eher extends ViewModel haben?

// Attributes

    public String imgSaved;

    private static Sketch sketch;

    // TODO: Ist es wirklich nötig, diese Attribute in Sketch UND MainActivityViewModel zu haben?
    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

    private List<CombinedShape> combinedShapes;

    // Constructor

    public MainActivityViewModel() {
        sketch = Sketch.getSketch();
        combinedShapes = new ArrayList<>();
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

    public void toggleEditMode() {
        sketch.toggleEditMode();
    }

    public boolean isEditModeOn() {
        return sketch.isEditModeTurnedOn();
    }

    public void saveSketch(Context context) {
        sketch.saveLayersToFile(context);
    }

    public void loadSketch(Context context) {
        sketch.loadLayersFromFile(context);
    }

    /**
     * Enable Combined Shapes mode: all selected Graphical Elements will be added to the current Combined Shape
     * @param combinedShape the current Combined Shape
     */
    public void enableCombinedShapesMode(CombinedShape combinedShape) {
        sketch.setCurrentCombinedShape(combinedShape);
        sketch.setCombineShapesModeOn(true);
        sketch.setEditModeTurnedOn(true);    // enable Edit Mode to prevent moving shapes at this point
        combinedShapes.add(combinedShape);
    }

    /**
     * Disable Combined Shapes mode: reset to initial values
     */
    public void disableCombinedShapesMode() {
        sketch.setCurrentCombinedShape(null);
        sketch.setCombineShapesModeOn(false);
        sketch.setEditModeTurnedOn(false);
    }

    public CombinedShape getCurrentCombinedShape() {
        return sketch.getCurrentCombinedShape();
    }

    public void resetSelection() {
        sketch.resetSelection();
    }

    public void storeElement() {
        sketch.storeElement();
    }

    public List<CombinedShape> getCombinedShapes() {
        return combinedShapes;
    }
}

