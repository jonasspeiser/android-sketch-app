package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import at.ac.univie.se2ws2020team0310.sketch_app.R;
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
        SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
        SeekBar textSizeSeekBar = findViewById(R.id.textSizeSeekBar);

        canvasView = (CanvasView) findViewById(R.id.canvasView);

        //Set Listeners
        findViewById(R.id.colorSelectorButton).setOnClickListener(colorPickerListener);
        findViewById(R.id.strokeWidthButton).setOnClickListener(strokeWidthButtonListener);

        strokeWidthSeekBar.setOnSeekBarChangeListener(strokeWidthSeekBarListener);
        textSizeSeekBar.setOnSeekBarChangeListener(textSizeSeekBarListener);
    }

    // Define Listeners
    private SeekBar.OnSeekBarChangeListener textSizeSeekBarListener = new SeekBar.OnSeekBarChangeListener(){
        int textSize = 0;

        public void onProgressChanged(SeekBar textSizeSeekBar, int progress, boolean fromUser) {
            textSize = progress;
            GraphicalElement.changeTextSize(textSize);
        }

        public void onStartTrackingTouch(SeekBar textSizeSeekBar) {
            // TODO Auto-generated method stub
        }

        public void onStopTrackingTouch(SeekBar textSizeSeekBar) {
        }
    };

    private SeekBar.OnSeekBarChangeListener strokeWidthSeekBarListener = new SeekBar.OnSeekBarChangeListener(){
        int strokeWidth = 0;

        public void onProgressChanged(SeekBar textSizeSeekBar, int progress, boolean fromUser) {
            strokeWidth = progress;
            GraphicalElement.changeStrokeWidth(strokeWidth);
        }

        public void onStartTrackingTouch(SeekBar textSizeSeekBar) {
            // TODO Auto-generated method stub
        }

        public void onStopTrackingTouch(SeekBar textSizeSeekBar) {
        }
    };

    private View.OnClickListener colorPickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ColorPicker colorPicker = new ColorPicker(MainActivity.this);
            colorPicker.show();
            colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                @Override
                public void onChooseColor(int position, int color) {
                    GraphicalElement.getSelectedPaint().setColor(color);
                }

                @Override
                public void onCancel() {
                }
            });
        }
    };

    private View.OnClickListener strokeWidthButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
            if (strokeWidthSeekBar.getVisibility() == SeekBar.VISIBLE) {
                strokeWidthSeekBar.setVisibility(View.INVISIBLE);
            } else {
                strokeWidthSeekBar.setVisibility(View.VISIBLE);
            }
        }
    };

    // Initialize menu
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    // Define SeekBar visibilities
    private void toggle_SeekBar_visibility(SeekBar seekBar){
        if(seekBar.getVisibility()==SeekBar.VISIBLE){
            seekBar.setVisibility(View.INVISIBLE);
        }else{
            seekBar.setVisibility(View.VISIBLE);
        }
    }

    // Define menu actions
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.textId:
                SeekBar textSizeSeekBar = findViewById(R.id.textSizeSeekBar);
                toggle_SeekBar_visibility(textSizeSeekBar);
                return true;

            case R.id.fingerId:
                // TODO
                return true;

            case R.id.lineId:
                // TODO
                return true;

            case R.id.circleId:
                canvasView.selectCircle();
                return true;

            case R.id.squareId:
                canvasView.selectQuadrangle();
                return true;

            case R.id.triangleId:
                // TODO
                return true;

            case R.id.loadId:
                // TODO
                return true;

            case R.id.saveId:
                // TODO
                return true;

            case R.id.exportId:
                // TODO
                return true;

            case R.id.deleteId:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}