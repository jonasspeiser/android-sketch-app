package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import at.ac.univie.se2ws2020team0310.sketch_app.R;
import at.ac.univie.se2ws2020team0310.sketch_app.databinding.ActivityMainBinding;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.EGraphicalElementType;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.Text;
import at.ac.univie.se2ws2020team0310.sketch_app.viewmodel.MainActivityViewModel;
import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

// Attributes

    ActivityMainBinding binding;
    private CanvasView canvasView;
    private MainActivityViewModel mainActivityViewModel;
    ToggleButton switchLayer1;
    ToggleButton switchLayer2;
    ToggleButton switchLayer3;
    RadioButton layer1selector;
    RadioButton layer2selector;
    RadioButton layer3selector;
    ContentResolver contentResolver;

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
        canvasView = findViewById(R.id.canvasView);
        mainActivityViewModel = new MainActivityViewModel();
        switchLayer1=(ToggleButton)findViewById(R.id.switchLayer1);
        switchLayer2=(ToggleButton)findViewById(R.id.switchLayer2);
        switchLayer3=(ToggleButton)findViewById(R.id.switchLayer3);
        layer1selector=(RadioButton)findViewById(R.id.layer1selector);
        layer2selector=(RadioButton)findViewById(R.id.layer2selector);
        layer3selector=(RadioButton)findViewById(R.id.layer3selector);
        contentResolver=getContentResolver();

        // Defining the logic on when the SeekBars/ColorPicker should be displayed
        SetStrokeWidthSeekBarBehavior();
        SetSizeSeekBarBehavior();
        SetColorPickerBehavior();
        SetLayerSelectionVisibility();

        //set canvas color to white, since JPG has troubles with transparent canvas
        canvasView.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        //gather content from canvas
        canvasView.setDrawingCacheEnabled(true);

        // Set the SeekBarChangeListeners
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar sizeSeekBar, int progress, boolean fromUser) {
                mainActivityViewModel.setSelectedSize(progress);
                mainActivityViewModel.changeElementSize(progress);
            }

            public void onStartTrackingTouch(SeekBar sizeSeekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar sizeSeekBar) {
            }
        });

        strokeWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar strokeWidthSeekBar, int progress, boolean fromUser) {
                mainActivityViewModel.setSelectedStrokeWidth(progress);
                mainActivityViewModel.changeElementStrokeWidth(progress);
            }

            public void onStartTrackingTouch(SeekBar strokeWidthSeekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar strokeWidthSeekBar) {
            }
        });

        switchLayer1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast layer0visibility;
                if (isChecked) {
                    mainActivityViewModel.setLayerVisibility(0,true);
                    layer0visibility = Toast.makeText(getApplicationContext(), "Layer 0 visible", Toast.LENGTH_LONG);
                } else {
                    mainActivityViewModel.setLayerVisibility(0, false);
                    layer0visibility = Toast.makeText(getApplicationContext(), "Layer 0 invisible", Toast.LENGTH_LONG);
                }
                layer0visibility.show();
                refreshScreen();
            }
        });

        switchLayer2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast layer1visibility;
                if (isChecked) {
                    mainActivityViewModel.setLayerVisibility(1,true);
                    layer1visibility = Toast.makeText(getApplicationContext(), "Layer 1 visible", Toast.LENGTH_LONG);
                } else {
                    mainActivityViewModel.setLayerVisibility(1, false);
                    layer1visibility = Toast.makeText(getApplicationContext(), "Layer 1 invisible", Toast.LENGTH_LONG);
                }
                layer1visibility.show();
                refreshScreen();
            }
        });

        switchLayer3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast layer2visibility;
                if (isChecked) {
                    mainActivityViewModel.setLayerVisibility(2,true);
                    layer2visibility = Toast.makeText(getApplicationContext(), "Layer 1 visible", Toast.LENGTH_LONG);
                } else {
                    mainActivityViewModel.setLayerVisibility(2, false);
                    layer2visibility = Toast.makeText(getApplicationContext(), "Layer 1 invisible", Toast.LENGTH_LONG);
                }
                layer2visibility.show();
                refreshScreen();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.layer1selector:
                if (checked)
                    mainActivityViewModel.selectLayer(0);
                    Toast layer0 = Toast.makeText(getApplicationContext(), "Layer 0 selected", Toast.LENGTH_LONG);
                    layer0.show();
                    break;
            case R.id.layer2selector:
                if (checked)
                    mainActivityViewModel.selectLayer(1);
                    Toast layer1 = Toast.makeText(getApplicationContext(), "Layer 1 selected", Toast.LENGTH_LONG);
                    layer1.show();
                    break;
            case R.id.layer3selector:
                if(checked)
                    mainActivityViewModel.selectLayer(2);
                    Toast layer2 = Toast.makeText(getApplicationContext(), "Layer 2 selected", Toast.LENGTH_LONG);
                    layer2.show();
                break;
        }
    }

    public void SetLayerSelectionVisibility() {
        ToggleButton switchLayer1 = findViewById(R.id.switchLayer1);
        ToggleButton switchLayer2 = findViewById(R.id.switchLayer2);
        ToggleButton switchLayer3 = findViewById(R.id.switchLayer3);
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
                if (mainActivityViewModel.layerIsEmpty()) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                }
                if (!mainActivityViewModel.layerIsEmpty()) {
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
                if (mainActivityViewModel.layerIsEmpty()) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                }
                if (!mainActivityViewModel.layerIsEmpty()) {
                    strokeWidthSeekBar.setVisibility(SeekBar.VISIBLE);
                }
            }
        });
    }

    public void SetColorPickerBehavior(){
        findViewById(R.id.colorSelectorButton).setOnClickListener(v -> {
            if (mainActivityViewModel.layerIsEmpty()) {
                Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                error.show();
            } else {
                ColorPicker colorPicker = new ColorPicker(MainActivity.this);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        mainActivityViewModel.setSelectedColor(color);
                        mainActivityViewModel.changeElementColor(color);
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

                mainActivityViewModel.selectGraphicalElement(EGraphicalElementType.TEXT_FIELD);
                showToast("Text selected");
                return true;

            case R.id.fingerId:
                mainActivityViewModel.selectGraphicalElement(EGraphicalElementType.FREEHAND);
                showToast("Freehand drawing selected");
                return true;

            case R.id.saveId:
                mainActivityViewModel.saveSketch(getApplicationContext());
                return true;

            case R.id.loadId:
                mainActivityViewModel.loadSketch(getApplicationContext());
                return true;

            case R.id.exportId:
                //ToDo move to ViewModel
                //ToDO Anonymus onClickListener
                //ToDo Success Toast Message wird nicht gezeigt
                //TODo Felix soll diesen Satz löschen!!!!!
                //Show window to user in order to confirm export to gallery
                AlertDialog.Builder exportDialogue = new AlertDialog.Builder(MainActivity.this);
                exportDialogue.setTitle("Save sketch");
                CharSequence[] fileFormatsSelection = new CharSequence[]{"JPEG","PNG"};
                exportDialogue.setItems(fileFormatsSelection, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                try {
                                    if(canvasView.export(MainActivity.this, "JPEG")==true){
                                        Toast jpegExport = Toast.makeText(getApplicationContext(), "JPEG Export successful!", Toast.LENGTH_LONG);
                                        jpegExport.show();
                                    };
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                try {
                                    if(canvasView.export(MainActivity.this, "PNG")==true){
                                        Toast pngExport = Toast.makeText(getApplicationContext(), "PNG Export successful!", Toast.LENGTH_LONG);
                                        pngExport.show();
                                    };

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    }
                });
                AlertDialog exportDialogueObject = exportDialogue.create();
                exportDialogueObject.show();
                return true;

            case R.id.lineId:
                mainActivityViewModel.selectGraphicalElement(EGraphicalElementType.LINE);
                showToast("Line selected");
                return true;

            case R.id.circleId:
                mainActivityViewModel.selectGraphicalElement(EGraphicalElementType.CIRCLE);
                showToast("Circle selected");
                return true;

            case R.id.squareId:
                mainActivityViewModel.selectGraphicalElement(EGraphicalElementType.QUADRANGLE);
               showToast("Quadrangle selected");
                return true;

            case R.id.triangleId:
                mainActivityViewModel.selectGraphicalElement(EGraphicalElementType.TRIANGLE);
                showToast("Triangle selected");
                return true;

            case R.id.deleteId:
                mainActivityViewModel.clearSketch();
                return true;

            case R.id.clearId:
                //ToDo move to ViewModel
                //ToDO Anonymus onClickListener
                //ToDO "clear" löscht Freehand meistens, bevor es confirmed ist
                //ToDo Success Toast Message wird nicht gezeigt
                //TODo Felix soll diesen Satz löschen!
                //In Anlehnung an https://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-essential-functionality--mobile-19328
                //Show window to user in order to confirm that the sketch should be deleted
                mainActivityViewModel.deleteElement();
                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("New Sketch");
                newDialog.setMessage("Start from scratch?");
                newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        mainActivityViewModel.clearSketch();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                newDialog.show();
                return true;

            case R.id.editModeId:
                mainActivityViewModel.toggleEditMode();
                showToast("Edit mode: " + (mainActivityViewModel.isEditModeOn() ? "ON" : "OFF"));
                return true;

            case R.id.newCombiShapeId:
                showToast("Create new Combined Shape");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: Funktionalität in Methode im ViewModel auslagern (ab hier bis unten)
    public void onClickDoneButton(View view) {

        //mainViewModel.selectGraphicalElement(EGraphicalElementType.TEXT_FIELD);
        Text mText = (Text) canvasView.getCanvasViewModel().getSelectedGraphicalElement();
        mText.setUserText(getEnteredText());

        refreshScreen();

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
        FloatingActionButton underlineText = findViewById(R.id.underlineButton);
        FloatingActionButton boldText = findViewById(R.id.boldButton);
        FloatingActionButton italicText = findViewById(R.id.italicButton);

        underlineText.setVisibility(View.VISIBLE);
        boldText.setVisibility(View.VISIBLE);
        italicText.setVisibility(View.VISIBLE);
    }

    public void hideTextStyleButtons() {
        FloatingActionButton underlineText = findViewById(R.id.underlineButton);
        FloatingActionButton boldText = findViewById(R.id.boldButton);
        FloatingActionButton italicText = findViewById(R.id.italicButton);

        underlineText.setVisibility(View.GONE);
        boldText.setVisibility(View.GONE);
        italicText.setVisibility(View.GONE);
    }

    public void onClickItalicButton(View view) {
        mainActivityViewModel.onClickItalicButton();
    }
    public void onClickBoldButton(View view) {
        mainActivityViewModel.onClickBoldButton();
    }
    public void onClickUnderlineButton(View view) {
        mainActivityViewModel.onClickUnderlineButton();
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


    //TODO: Can we get rid of this method? It would be better not to call a canvasView method directly from here
    public void refreshScreen() {
        canvasView.invalidate();
    }


}
