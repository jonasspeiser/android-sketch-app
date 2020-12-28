package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import android.graphics.Color;
import android.graphics.Paint;

import androidx.lifecycle.ViewModel;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;

public class MainViewModel extends ViewModel {

// Attributes

    private Sketch sketch;
    private Paint selectedPaint;

// Constructor
    public MainViewModel() {
        this.sketch = Sketch.getSketch();
        initializePaint();
    }
// Getters and Setters

    public Paint getSelectedPaint() {
        return selectedPaint;
    }
    public void setSelectedPaint(Paint selectedPaint) {
        this.selectedPaint = selectedPaint;
    }

// Other Methods
    public void initializePaint() {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
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

