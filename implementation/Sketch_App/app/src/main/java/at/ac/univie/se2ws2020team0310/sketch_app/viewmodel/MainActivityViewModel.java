package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.BR;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.decorators.BoldDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.ElementNotFoundException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class MainActivityViewModel extends BaseObservable {

    private static final String MAIN_ACTIVITY_VIEW_MODEL = "MainActivityViewModel";

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
    public void setSelectedStrokeWidth(
            float selectedStrokeWidth) {     // TODO: Was genau tut das? Wieso gibts das nur bei Strokewidth?
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

    public void deleteElement() {
        sketch.deleteElement();
    }

    public void clearSketch() {
        sketch.clear();
    }


    public void selectLayer(int layerNumber) {
        sketch.setSelectedLayerIndex(layerNumber);
    }

    public void setLayerVisibility(int layerNumber, boolean isVisible) {
        sketch.setLayerVisibility(layerNumber, isVisible);
    }

    public boolean layerIsEmpty() {
        return sketch.layerIsEmpty();
    }


    public void onClickItalicButton() {
        sketch.selectItalic();
    }

    public void onClickBoldButton() {
        sketch.selectBold();
    }

    public void onClickUnderlineButton() {
        sketch.selectUnderline();
    }

    /**
     * Create and select a new GraphicalElement with the given type
     *
     * @param type the type of the GraphicalElement
     */
    public void selectGraphicalElement(EGraphicalElementType type) {
        sketch.selectGraphicalElement(type);
    }

    /**
     * Create and select a new GraphicalElement as a copy of the given one as parameter
     *
     * @param element the element to copy from
     */
    public void selectGraphicalElement(GraphicalElement element) {
        sketch.selectGraphicalElement(element);
    }

    public void toggleEditMode() {
        sketch.toggleEditMode();
    }

    public boolean isEditModeOn() {
        return sketch.isEditModeTurnedOn();
    }

    public void saveSketch(Context context, int saveslot) {
        sketch.saveLayersToFile(context, saveslot);
    }

    public void loadSketch(Context context, int saveslot) throws NullPointerException {
        sketch.loadLayersFromFile(context, saveslot);
    }

    public void deleteSavedSketches(Context context) {
        sketch.deleteSavedSketches(context);
    }

    /**
     * Enable Combined Shapes mode: all selected Graphical Elements will be added to the current
     * Combined Shape
     *
     * @param combinedShape the current Combined Shape
     */
    public void enableCombinedShapesMode(CombinedShape combinedShape) {
        sketch.setCurrentCombinedShape(combinedShape);
        sketch.setCombineShapesModeOn(true);
        sketch.setEditModeTurnedOn(
                true);    // enable Edit Mode to prevent moving shapes at this point
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

    /**
     * Check if the created Combined Shape contains at least one element If the Combined Shape is
     * empty, then remove its references
     *
     * @throws AppException if the Combined Shape contains no elements
     */
    public void processCurrentCombinedShape() throws AppException {
        CombinedShape combinedShape = getCurrentCombinedShape();
        if (combinedShape == null) {
            Log.w(MAIN_ACTIVITY_VIEW_MODEL, "Combined Shape is null");
            throw new AppException("An error occurred: No Combined Shape found");
        }
        if (combinedShape.getElements().isEmpty()) {
            combinedShapes.remove(combinedShape);
            try {
                // attempt to remove the Combined Shape from the current Sketch
                sketch.removeElement(combinedShape);
            } catch (ElementNotFoundException e) {
                Log.w(MAIN_ACTIVITY_VIEW_MODEL,
                        "Error while removing Combined Shape from sketch: " + e
                                .getLocalizedMessage());
            }
            Log.w(MAIN_ACTIVITY_VIEW_MODEL,
                    combinedShape + " has no elements selected, ignoring it");
            throw new AppException("No elements selected for the Combined Shape");
        }
        // remove elements within Combined Shape from current list of drawn elements
        sketch.removeElements(combinedShape.getElements());
    }

    /**
     * Sets the entered text as the name of the current Combined Shape
     *
     * @param enteredText
     */
    public void setCurrentCombinedShapeName(String enteredText) {
        if (getCurrentCombinedShape() != null) {
            getCurrentCombinedShape().setName(enteredText);
        }
    }
}

