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
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import java.io.IOException;

import at.ac.univie.se2ws2020team0310.sketch_app.R;
import at.ac.univie.se2ws2020team0310.sketch_app.databinding.ActivityMainBinding;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import at.ac.univie.se2ws2020team0310.sketch_app.viewmodel.MainViewModel;
import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

// Attributes

    ActivityMainBinding binding;
    private CanvasView canvasView;
    private MainViewModel mainViewModel;

// Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SeekBar sizeSeekBar = findViewById(R.id.sizeSeekBar);
        SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
        Switch switchLayer1 = findViewById(R.id.switchLayer1);
        Switch switchLayer2 = findViewById(R.id.switchLayer2);
        Switch switchLayer3 = findViewById(R.id.switchLayer3);
        RadioButton layer1selector = findViewById(R.id.layer1selector);
        RadioButton layer2selector = findViewById(R.id.layer1selector);
        RadioButton layer3selector = findViewById(R.id.layer1selector);
        canvasView = findViewById(R.id.canvasView);
        mainViewModel = new MainViewModel();

        // Defining the logic on when the SeekBars/ColorPicker should be displayed
        SetStrokeWidthSeekBarBehavior();
        SetSizeSeekBarBehavior();
        SetColorPickerBehavior();
        SetLayerSelectionVisibility();

        // Set the SeekBarChangeListeners
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar sizeSeekBar, int progress, boolean fromUser) {
                mainViewModel.setSelectedSize(progress);
                canvasView.changeElementSize(progress);
                //TODO: change object size
            }

            public void onStartTrackingTouch(SeekBar sizeSeekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar sizeSeekBar) {
            }
        });

        strokeWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar strokeWidthSeekBar, int progress, boolean fromUser) {
                mainViewModel.setSelectedStrokeWidth(progress);
                canvasView.changeElementStrokeWidth(progress);
            }

            public void onStartTrackingTouch(SeekBar strokeWidthSeekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar strokeWidthSeekBar) {
            }
        });

    }

    public void SetLayerVisibility(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.layer1selector:
                if (checked)
                    mainViewModel.selectLayer(0);
                    break;
            case R.id.layer2selector:
                if (checked)
                    mainViewModel.selectLayer(1);
                break;
            case R.id.layer3selector:
                if (checked)
                    mainViewModel.selectLayer(2);
                    break;
        }
    }

    public void SetLayerSelectionVisibility() {
        Switch switchLayer1 = findViewById(R.id.switchLayer1);
        Switch switchLayer2 = findViewById(R.id.switchLayer2);
        Switch switchLayer3 = findViewById(R.id.switchLayer3);
        RadioButton layer1selector = findViewById(R.id.layer1selector);
        RadioButton layer2selector = findViewById(R.id.layer2selector);
        RadioButton layer3selector = findViewById(R.id.layer3selector);

        findViewById(R.id.layerswitchbutton).setOnClickListener(v -> {
            if(switchLayer1.getVisibility() == Switch.VISIBLE) {
                switchLayer1.setVisibility(Switch.INVISIBLE);
                switchLayer2.setVisibility(Switch.INVISIBLE);
                switchLayer3.setVisibility(Switch.INVISIBLE);
                layer1selector.setVisibility(RadioButton.INVISIBLE);
                layer2selector.setVisibility(RadioButton.INVISIBLE);
                layer3selector.setVisibility(RadioButton.INVISIBLE);
            } else {
                switchLayer1.setVisibility(Switch.VISIBLE);
                switchLayer2.setVisibility(Switch.VISIBLE);
                switchLayer3.setVisibility(Switch.VISIBLE);
                layer1selector.setVisibility(RadioButton.VISIBLE);
                layer2selector.setVisibility(RadioButton.VISIBLE);
                layer3selector.setVisibility(RadioButton.VISIBLE);
            }
        });
    }

    public void SetSizeSeekBarBehavior() {
        SeekBar sizeSeekBar = findViewById(R.id.sizeSeekBar);

        findViewById(R.id.sizeButton).setOnClickListener(v -> {
            if (sizeSeekBar.getVisibility() == SeekBar.VISIBLE) {
                sizeSeekBar.setVisibility(SeekBar.INVISIBLE);
            } else {
                if (mainViewModel.layerIsEmpty()) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                }
                if (!mainViewModel.layerIsEmpty()) {
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
                if (mainViewModel.layerIsEmpty()) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                }
                if (!mainViewModel.layerIsEmpty()) {
                    strokeWidthSeekBar.setVisibility(SeekBar.VISIBLE);
                }
            }
        });
    }

    public void SetColorPickerBehavior(){
        findViewById(R.id.colorSelectorButton).setOnClickListener(v -> {
            if (mainViewModel.layerIsEmpty()) {
                Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                error.show();
            } else {
                ColorPicker colorPicker = new ColorPicker(MainActivity.this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        mainViewModel.setSelectedColor(color);
                        canvasView.changeElementColor(color); //TODO: in ViewModel: Der Regler soll machen: onChange notify. Wir brauchen einen Observer, der den Wert dieses Reglers abgreift
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

        //TODO:Edit Mode implementation!
        switch (item.getItemId()) {

            case R.id.textId:
                showTextEntryField();
                showTextStyleButtons();

                mainViewModel.selectGraphicalElement(EGraphicalElementType.TEXT_FIELD);
                showToast("Text selected");
                return true;

            case R.id.fingerId:
                mainViewModel.selectGraphicalElement(EGraphicalElementType.FREEHAND);
                showToast("Freehand drawing selected");
                return true;

            case R.id.saveId:
            case R.id.loadId:
            case R.id.exportId:
                canvasView.saveToInternalStorage();
               return true;

            case R.id.lineId:
                mainViewModel.selectGraphicalElement(EGraphicalElementType.LINE);
                showToast("Line selected");
                return true;

            case R.id.circleId:
                mainViewModel.selectGraphicalElement(EGraphicalElementType.CIRCLE);
                showToast("Circle selected");
                return true;

            case R.id.squareId:
                mainViewModel.selectGraphicalElement(EGraphicalElementType.QUADRANGLE);
               showToast("Quadrangle selected");
                return true;

            case R.id.triangleId:
                mainViewModel.selectGraphicalElement(EGraphicalElementType.TRIANGLE);
                showToast("Triangle selected");
                return true;

            case R.id.deleteId:
                canvasView.deleteElement();
                return true;

            case R.id.clearId:
                canvasView.clear();
                return true;

            case R.id.editModeId:
                mainViewModel.toggleEditMode();
                showToast("Edit mode: " + (mainViewModel.isEditModeOn() ? "ON" : "OFF"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: Funktionalität in Methode im ViewModel auslagern (ab hier bis unten)
    public void onClickDoneButton(View view) {

        mainViewModel.selectGraphicalElement(EGraphicalElementType.TEXT_FIELD);
        Text mText = (Text) canvasView.getCanvasViewModel().getSelectedGraphicalElement();
        mText.setUserText(getEnteredText());

        canvasView.invalidate();

        hideTextEntryField();
        hideTextStyleButtons();
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

    public void showTextStyleButtons(){
        Button underlineText = findViewById(R.id.underlineButton);
        Button boldText = findViewById(R.id.boldButton);
        Button italicText = findViewById(R.id.italicButton);

        underlineText.setVisibility(View.VISIBLE);
        boldText.setVisibility(View.VISIBLE);
        italicText.setVisibility(View.VISIBLE);
    }

    public void hideTextStyleButtons() {
        Button underlineText = findViewById(R.id.underlineButton);
        Button boldText = findViewById(R.id.boldButton);
        Button italicText = findViewById(R.id.italicButton);

        underlineText.setVisibility(View.GONE);
        boldText.setVisibility(View.GONE);
        italicText.setVisibility(View.GONE);
    }

    public void onClickStyleButtons(View view) {
        mainViewModel.buttonClick();
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

    public void showToast(String text){
        Toast textToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        textToast.show();
    }
}
