package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import at.ac.univie.se2ws2020team0310.sketch_app.R;
import at.ac.univie.se2ws2020team0310.sketch_app.databinding.ActivityMainBinding;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private CanvasView canvasView;
    static private Paint selectedPaint;


    public static Paint getSelectedPaint() {
        return MainActivity.selectedPaint;
    }
    public static void setSelectedPaint(Paint selectedPaint) {
        MainActivity.selectedPaint = selectedPaint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SeekBar sizeSeekBar = findViewById(R.id.sizeSeekBar);
        SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
        canvasView = findViewById(R.id.canvasView);

        // Defining the logic on when the SeekBars/ColorPicker should be displayed
        SetStrokeWidthSeekBarBehavior();
        SetSizeSeekBarBehavior();
        SetColorPickerBehavior();

        // Set the SeekBarChangeListeners
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar sizeSeekBar, int progress, boolean fromUser) {
                selectedPaint.setTextSize(progress);
                canvasView.changeElementTextSize(progress);
                //TODO: change object size
                canvasView.invalidate();
            }

            public void onStartTrackingTouch(SeekBar sizeSeekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar sizeSeekBar) {
            }
        });

        strokeWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar strokeWidthSeekBar, int progress, boolean fromUser) {
                selectedPaint.setStrokeWidth(progress);
                canvasView.changeElementStrokeWidth(progress);
                canvasView.invalidate();
            }

            public void onStartTrackingTouch(SeekBar strokeWidthSeekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar strokeWidthSeekBar) {
            }
        });

    }

    public void SetSizeSeekBarBehavior() {
        SeekBar sizeSeekBar = findViewById(R.id.sizeSeekBar);

        findViewById(R.id.sizeButton).setOnClickListener(v -> {
            if (sizeSeekBar.getVisibility() == SeekBar.VISIBLE) {
                sizeSeekBar.setVisibility(SeekBar.INVISIBLE);
            } else {
                if (canvasView.getAppViewModel().layerIsEmpty()) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                }
                if (!canvasView.getAppViewModel().layerIsEmpty()) {
                    sizeSeekBar.setVisibility(SeekBar.VISIBLE);
                }
            }
        });
    }

    public void SetStrokeWidthSeekBarBehavior(){
        SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);

        findViewById(R.id.strokeWidthButton).setOnClickListener(v -> {
            if(strokeWidthSeekBar.getVisibility()==SeekBar.VISIBLE){
                strokeWidthSeekBar.setVisibility(SeekBar.INVISIBLE);
            }else{
                if (canvasView.getAppViewModel().layerIsEmpty()) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                }
                if (!canvasView.getAppViewModel().layerIsEmpty()) {
                    strokeWidthSeekBar.setVisibility(SeekBar.VISIBLE);
                }
            }
        });
    }

    public void SetColorPickerBehavior(){
        findViewById(R.id.colorSelectorButton).setOnClickListener(v -> {
            if (canvasView.getAppViewModel().layerIsEmpty()) {
                Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                error.show();
            } else {
                ColorPicker colorPicker = new ColorPicker(MainActivity.this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        selectedPaint.setColor(color);
                        canvasView.changeElementColor(color); //TODO: in ViewModel: Der Regler soll machen: onChange notify. Wir brauchen einen Observer, der den Wert dieses Reglers abgreift
                        canvasView.invalidate();
                    }

                    @Override
                    public void onCancel() {
                        // put code
                    }
                });
            }
        });

        initializePaint();
    }

    public void initializePaint() {
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        MainActivity.setSelectedPaint(mPaint);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    //The implementation of the load, save and export-functions will be included for DEAD.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.textId:
                showTextEntryField();

                canvasView.getAppViewModel().selectText(selectedPaint);
                Toast textToast = Toast.makeText(getApplicationContext(), "Text selected", Toast.LENGTH_LONG);
                textToast.show();
                return true;

            case R.id.fingerId:
            case R.id.saveId:
            case R.id.loadId:
            case R.id.exportId:
                // TODO
                return true;

            case R.id.lineId:
                canvasView.getAppViewModel().selectLine(selectedPaint);
                Toast lineToast = Toast.makeText(getApplicationContext(), "Line selected", Toast.LENGTH_LONG);
                lineToast.show();
                return true;

            case R.id.circleId:
                canvasView.getAppViewModel().selectCircle(selectedPaint);
                Toast circleToast = Toast.makeText(getApplicationContext(), "Circle selected", Toast.LENGTH_LONG);
                circleToast.show();
                return true;

            case R.id.squareId:
                canvasView.getAppViewModel().selectQuadrangle(selectedPaint);
                Toast quadrangleToast = Toast.makeText(getApplicationContext(), "Quadrangle selected", Toast.LENGTH_LONG);
                quadrangleToast.show();
                return true;

            case R.id.triangleId:
                canvasView.getAppViewModel().selectTriangle(selectedPaint);
                Toast triangleToast = Toast.makeText(getApplicationContext(), "Triangle selected", Toast.LENGTH_LONG);
                triangleToast.show();
                return true;

            case R.id.deleteId:
                canvasView.deleteElement();
                return true;

            case R.id.clearId:
                canvasView.clear();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: Move this to CanvasView or in another class?
    public void onClickDoneButton(View view) {

        canvasView.getAppViewModel().selectText(selectedPaint);
        Text mText = (Text) canvasView.getAppViewModel().getSelectedGraphicalElement();
        mText.setUserText(getEnteredText());

        canvasView.invalidate();

        hideTextEntryField();
    }

    public String getEnteredText(){
        EditText editText = findViewById(R.id.editText);
        return editText.getText().toString();
    }

    public void showTextEntryField(){
        EditText editText = findViewById(R.id.editText);
        Button toggleText = findViewById(R.id.toggleText);

        editText.setVisibility(View.VISIBLE);
        toggleText.setVisibility(View.VISIBLE);
    }

    public void hideTextEntryField() {
        EditText editText = findViewById(R.id.editText);
        Button toggleText = findViewById(R.id.toggleText);

        editText.setVisibility(View.GONE);
        toggleText.setVisibility(View.GONE);
    }


    // Hide the Soft Keyboard
    // solution from: https://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
