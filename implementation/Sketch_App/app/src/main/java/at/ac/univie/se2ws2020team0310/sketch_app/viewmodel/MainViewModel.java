package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.graphics.Color;
import android.graphics.Paint;

import androidx.lifecycle.ViewModel;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;

public class MainViewModel extends ViewModel {

// Attributes

    private Sketch sketch;
    private Paint selectedPaint;

    private int selectedColor;
    private float selectedSize;
    private float selectedStrokeWidth;

// Constructor
    public MainViewModel() {
        this.sketch = Sketch.getSketch();
        this.selectedColor = Color.BLACK;
        this.selectedSize = 150;
        this.selectedStrokeWidth = 15;
        initializePaint();
    }
// Getters and Setters

    public Paint getSelectedPaint() {
        return selectedPaint;
    }
    public void setSelectedPaint(Paint selectedPaint) {
        this.selectedPaint = selectedPaint;
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

    public void setSelectedStrokeWidth(float selectedStrokeWidth) {
        this.selectedStrokeWidth = selectedStrokeWidth;
    }

// Other Methods
    public void initializePaint() {
        Paint mPaint = new Paint();
        mPaint.setColor(this.selectedColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(this.selectedStrokeWidth);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(this.selectedSize / 3);
        this.setSelectedPaint(mPaint);
    }

    public void selectLine() {
        sketch.selectLine(this.selectedPaint);
    }

    public void selectCircle() {
        sketch.selectCircle(this.selectedPaint);
    }

    public void selectQuadrangle() {
        sketch.selectQuadrangle(this.selectedPaint);
    }

    public void selectTriangle() {
        sketch.selectTriangle(this.selectedPaint);
    }

    public void selectText() {
        sketch.selectText(this.selectedPaint);
    }

    public boolean layerIsEmpty() {return sketch.layerIsEmpty();}


}

