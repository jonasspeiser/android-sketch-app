package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.content.Context;
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

import at.ac.univie.se2ws2020team0310.sketch_app.R;
import at.ac.univie.se2ws2020team0310.sketch_app.model.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.model.Text;
import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SeekBar sizeSeekBar = findViewById(R.id.sizeSeekBar);
        SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
        canvasView = findViewById(R.id.canvasView);

        // Define display logic of Seekbars and the ColorPicker Palette
        SetSeekBarBehaviors();
        ColorPickerBehavior();

        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar sizeSeekBar, int progress, boolean fromUser) {
                GraphicalElement.getSelectedPaint().setTextSize(progress);
                canvasView.getLastElement().getObjectPaint().setTextSize(progress);
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
                GraphicalElement.getSelectedPaint().setStrokeWidth(progress);
                canvasView.getLastElement().getObjectPaint().setStrokeWidth(progress);
                canvasView.invalidate();
            }

            public void onStartTrackingTouch(SeekBar strokeWidthSeekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar strokeWidthSeekBar) {
            }
        });

    }

    public void SetSeekBarBehaviors(){
        SeekBar sizeSeekBar = findViewById(R.id.sizeSeekBar);
        SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);

        findViewById(R.id.sizeButton).setOnClickListener(v -> {
            if(sizeSeekBar.getVisibility()==SeekBar.VISIBLE){
                sizeSeekBar.setVisibility(SeekBar.INVISIBLE);
            }else{
                if (canvasView.getSelectedGraphicalElement() == null) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                }
                if (canvasView.getSelectedGraphicalElement() != null) {
                    sizeSeekBar.setVisibility(SeekBar.VISIBLE);
                }                }
        });

        findViewById(R.id.strokeWidthButton).setOnClickListener(v -> {
            if(strokeWidthSeekBar.getVisibility()==SeekBar.VISIBLE){
                strokeWidthSeekBar.setVisibility(SeekBar.INVISIBLE);
            }else{
                if (canvasView.getSelectedGraphicalElement() == null) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                }
                if (canvasView.getSelectedGraphicalElement() != null) {
                    strokeWidthSeekBar.setVisibility(SeekBar.VISIBLE);
                }
            }
        });
    }

    public void ColorPickerBehavior(){
        findViewById(R.id.colorSelectorButton).setOnClickListener(v -> {
            if (canvasView.getSelectedGraphicalElement() == null) {
                Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                error.show();
            } else {
                ColorPicker colorPicker = new ColorPicker(MainActivity.this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        GraphicalElement.getSelectedPaint().setColor(color);
                        canvasView.getLastElement().getObjectPaint().setColor(color);
                        canvasView.invalidate();
                    }

                    @Override
                    public void onCancel() {
                        // put code
                    }
                });
            }
        });

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
                EditText editText = findViewById(R.id.editText);
                Button toggleText = findViewById(R.id.toggleText);
                editText.setVisibility(View.VISIBLE);
                toggleText.setVisibility(View.VISIBLE);

                canvasView.selectText();
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
                canvasView.selectLine();
                Toast lineToast = Toast.makeText(getApplicationContext(), "Line selected", Toast.LENGTH_LONG);
                lineToast.show();
                return true;

            case R.id.circleId:
                canvasView.selectCircle();
                Toast circleToast = Toast.makeText(getApplicationContext(), "Circle selected", Toast.LENGTH_LONG);
                circleToast.show();
                return true;

            case R.id.squareId:
                canvasView.selectQuadrangle();
                Toast quadrangleToast = Toast.makeText(getApplicationContext(), "Quadrangle selected", Toast.LENGTH_LONG);
                quadrangleToast.show();
                return true;

            case R.id.triangleId:
                canvasView.selectTriangle();
                Toast triangleToast = Toast.makeText(getApplicationContext(), "Triangle selected", Toast.LENGTH_LONG);
                triangleToast.show();
                return true;

            case R.id.deleteId:
                canvasView.clear();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: Move this to CanvasView or in another class?
    public void printText (View view) {
        EditText editText = findViewById(R.id.editText);
        Button toggleText = findViewById(R.id.toggleText);

        canvasView.selectText();
        Text mText = (Text) canvasView.getSelectedGraphicalElement();
        mText.setTextInput(editText.getText().toString());

        canvasView.invalidate();

        editText.setVisibility(View.GONE);
        toggleText.setVisibility(View.GONE);
    }


    // Hide the keyboard
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
