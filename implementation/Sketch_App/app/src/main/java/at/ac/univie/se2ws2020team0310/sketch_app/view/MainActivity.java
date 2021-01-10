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
import android.view.SubMenu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
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
import at.ac.univie.se2ws2020team0310.sketch_app.model.customExceptions.AppException;
import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.CombinedShape;
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
                if (isChecked) {
                    mainActivityViewModel.setLayerVisibility(0,true);
                    showToast("Layer 0 visible");
                } else {
                    mainActivityViewModel.setLayerVisibility(0, false);
                    showToast("Layer 0 invisible");
                }
                refreshScreen();
            }
        });

        switchLayer2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mainActivityViewModel.setLayerVisibility(1,true);
                    showToast("Layer 1 visible");
                } else {
                    mainActivityViewModel.setLayerVisibility(1, false);
                    showToast("Layer 1 invisible");
                }
                refreshScreen();
            }
        });

        switchLayer3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mainActivityViewModel.setLayerVisibility(2,true);
                    showToast("Layer 2 visible");
                } else {
                    mainActivityViewModel.setLayerVisibility(2, false);
                    showToast("Layer 2 invisible");
                }
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
                    showToast("Layer 0 selected");
                    break;
            case R.id.layer2selector:
                if (checked)
                    mainActivityViewModel.selectLayer(1);
                    showToast("Layer 1 selected");
                    break;
            case R.id.layer3selector:
                if(checked)
                    mainActivityViewModel.selectLayer(2);
                showToast("Layer 2 selected");
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
                    showToast("No graphical element selected");
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
                    showToast("No graphical element selected");
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
                showToast("No graphical element selected");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        addCombinedShapesMenuItems(menu);
        return true;
    }

    /**
     * For each saved Combined Shape, add a MenuItem under the SubMenu
     * @param menu  the current Menu
     */
    private void addCombinedShapesMenuItems(Menu menu) {
        SubMenu subMenu = menu.findItem(R.id.combiShapeId).getSubMenu();
        for (CombinedShape combinedShape : mainActivityViewModel.getCombinedShapes()) {
            // add each saved Combined Shape to the SubMenu
            MenuItem menuItem = subMenu
                    .add(combinedShape.getName());
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    mainActivityViewModel.selectGraphicalElement(combinedShape);
                    showToast("Combined Shape " + item.getTitle() + " selected");
                    return false;
                }
            });
        }
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
                AlertDialog.Builder saveDialogue = new AlertDialog.Builder(MainActivity.this);
                saveDialogue.setTitle("Save to Slot");
                CharSequence[] saveSlotSelection = new CharSequence[]{"1","2","3","4","5"};
                saveDialogue.setItems(saveSlotSelection, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                try {
                                    mainActivityViewModel.saveSketch(getApplicationContext(), 1);{
                                        showToast("Sketch saved to slot 1");
                                    };
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                try {
                                    mainActivityViewModel.saveSketch(getApplicationContext(), 2);{
                                        showToast("Sketch saved to slot 2");
                                    };
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                try {
                                    mainActivityViewModel.saveSketch(getApplicationContext(), 3);{
                                        showToast("Sketch saved to slot 3");
                                    };
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                try {
                                    mainActivityViewModel.saveSketch(getApplicationContext(), 4);{
                                        showToast("Sketch saved to slot4");
                                    };
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                try {
                                    mainActivityViewModel.saveSketch(getApplicationContext(), 5);{
                                        showToast("Sketch saved to slot 5");
                                    };
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    }
                });
                AlertDialog saveDialogueObject = saveDialogue.create();
                saveDialogueObject.show();
                return true;

            case R.id.loadId:
                AlertDialog.Builder loadDialogue = new AlertDialog.Builder(MainActivity.this);
                loadDialogue.setTitle("Open from Slot");
                CharSequence[] loadSlotSelection = new CharSequence[]{"1","2","3","4","5"};
                loadDialogue.setItems(loadSlotSelection, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                try {
                                    mainActivityViewModel.loadSketch(getApplicationContext(), 1); // TODO: Saveslot je nach Userwahl übergeben
                                    showToast("Sketch opened from slot 1");
                                } catch (NullPointerException e) { // TODO: Können wir durch eine Custom Exception ersetzen (in Sketch Klasse)
                                    e.printStackTrace();
                                    showToast("Selected Saveslot is empty");
                                }
                                break;
                            case 1:
                                try {
                                    mainActivityViewModel.loadSketch(getApplicationContext(), 2); // TODO: Saveslot je nach Userwahl übergeben
                                    showToast("Sketch opened from slot 2");
                                } catch (NullPointerException e) { // TODO: Können wir durch eine Custom Exception ersetzen (in Sketch Klasse)
                                    e.printStackTrace();
                                    showToast("Selected Saveslot is empty");
                                }
                                break;
                            case 2:
                                try {
                                    mainActivityViewModel.loadSketch(getApplicationContext(), 3); // TODO: Saveslot je nach Userwahl übergeben
                                    showToast("Sketch opened from slot 3");
                                } catch (NullPointerException e) { // TODO: Können wir durch eine Custom Exception ersetzen (in Sketch Klasse)
                                    e.printStackTrace();
                                    showToast("Selected Saveslot is empty");
                                }
                                break;
                            case 3:
                                try {
                                    mainActivityViewModel.loadSketch(getApplicationContext(), 4); // TODO: Saveslot je nach Userwahl übergeben
                                    showToast("Sketch opened from slot 4");
                                } catch (NullPointerException e) { // TODO: Können wir durch eine Custom Exception ersetzen (in Sketch Klasse)
                                    e.printStackTrace();
                                    showToast("Selected Saveslot is empty");
                                }
                                break;
                            case 4:
                                try {
                                    mainActivityViewModel.loadSketch(getApplicationContext(), 5); // TODO: Saveslot je nach Userwahl übergeben
                                    showToast("Sketch opened from slot 5");
                                } catch (NullPointerException e) { // TODO: Können wir durch eine Custom Exception ersetzen (in Sketch Klasse)
                                    e.printStackTrace();
                                    showToast("Selected Saveslot is empty");
                                }
                                break;
                        }
                    }
                });
                AlertDialog loadDialogueObject = loadDialogue.create();
                loadDialogueObject.show();
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
                                        showToast("JPEG Export successful!");
                                    };
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                try {
                                    if(canvasView.export(MainActivity.this, "PNG")==true){
                                        showToast("PNG Export successful!");                                    };

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

            case R.id.deleteStorageId:
                mainActivityViewModel.deleteSavedSketches(getApplicationContext());
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
                mainActivityViewModel.selectGraphicalElement(EGraphicalElementType.COMBINED_SHAPE);
                mainActivityViewModel.enableCombinedShapesMode((CombinedShape) canvasView.getSelectedGraphicalElement());
                showCombinedShapeNameTextForm();
                showToast("Give a name to the new Combined Shape");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: Funktionalität in Methode im ViewModel auslagern (ab hier bis unten)
    /**
     *
     * @param view required by Android interface
     */
    public void onClickDoneButton(View view) {

        Text mText = (Text) canvasView.getSelectedGraphicalElement();
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

        editText.setText("", TextView.BufferType.EDITABLE);
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

    public void showCombinedShapeNameTextForm(){
        EditText editText = findViewById(R.id.editText);
        Button nameCombinedShape = findViewById(R.id.nameCombiShape);

        editText.setVisibility(View.VISIBLE);
        nameCombinedShape.setVisibility(View.VISIBLE);

        editText.setText("", TextView.BufferType.EDITABLE);
    }

    public void hideCombinedShapeNameTextForm(){
        EditText editText = findViewById(R.id.editText);
        Button nameCombinedShape = findViewById(R.id.nameCombiShape);

        editText.setVisibility(View.GONE);
        nameCombinedShape.setVisibility(View.GONE);
    }

    public void showCombinedShapeSaveButton() {
        Button saveCombinedShape = findViewById(R.id.saveCombiShape);
        saveCombinedShape.setVisibility(View.VISIBLE);
    }

    public void hideCombinedShapeSaveButton() {
        Button saveCombinedShape = findViewById(R.id.saveCombiShape);
        saveCombinedShape.setVisibility(View.GONE);
    }

    /**
     * Method handler for setting the name of a new Combined Shape
     * @param view required by Android interface
     */
    public void onSetCombinedShapeName(View view) {
        String enteredText = getEnteredText();
        if (enteredText.trim().isEmpty()) {
            showToast("Name cannot be empty");
            return;
        }

        mainActivityViewModel.setCurrentCombinedShapeName(enteredText);

        mainActivityViewModel.storeElement();   // store the current Combined Shape
        mainActivityViewModel.resetSelection(); // reset selected graphical element

        hideCombinedShapeNameTextForm();
        showCombinedShapeSaveButton();
        showToast("Select the Shapes to combine");
    }

    /**
     * Method handler for saving a new Combined Shape with the selected Graphical Elements
     * @param view required by Android interface
     */
    public void onSaveCombinedShape(View view) {
        try {
            hideCombinedShapeSaveButton();
            mainActivityViewModel.processCurrentCombinedShape();
            CombinedShape combinedShape = mainActivityViewModel.getCurrentCombinedShape();
            showToast( "New Combined Shape saved: " + combinedShape.getName() + " (" + combinedShape.getElementsCount() + " elements)");
            invalidateOptionsMenu();
        } catch (AppException e) {
            showToast(e.getLocalizedMessage());
        }
        mainActivityViewModel.disableCombinedShapesMode();
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

    private void showToast(String text){
        ViewUtils.showToast(getApplicationContext(), text);
    }
    //TODO: Can we get rid of this method? It would be better not to call a canvasView method directly from here

    public void refreshScreen() {
        canvasView.invalidate();
    }
}
