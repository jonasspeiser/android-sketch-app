package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

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
        SeekBar seekBar = findViewById(R.id.SeekBar);

        // TODO: create method for this
        findViewById(R.id.sizeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBar strokeWidthSeekBar = findViewById(R.id.SeekBar);
                if(strokeWidthSeekBar.getVisibility()==SeekBar.VISIBLE){
                    strokeWidthSeekBar.setVisibility(View.INVISIBLE);
                }else{
                    strokeWidthSeekBar.setVisibility(View.VISIBLE);
                }
            }
        });

        // TODO: create method for this
        findViewById(R.id.colorSelectorButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    public void onCancel(){
                        // put code
                    }
                });
            }
        });


        canvasView = (CanvasView) findViewById(R.id.canvasView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                GraphicalElement.getSelectedPaint().setStrokeWidth(progress);
                GraphicalElement.changeTextSize(progress);
                canvasView.getLastElement().getObjectPaint().setTextSize(progress);
                canvasView.getLastElement().getObjectPaint().setStrokeWidth(progress);
                canvasView.invalidate();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.textId:

                EditText editText = (EditText) findViewById(R.id.editText);
                Button toggleText = findViewById(R.id.toggleText);
                editText.setVisibility(View.VISIBLE);
                toggleText.setVisibility(View.VISIBLE);

                canvasView.selectText();
                Toast textToast = Toast.makeText(getApplicationContext(), "Text selected", Toast.LENGTH_LONG);
                textToast.show();
                return true;

            case R.id.fingerId:
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
        mText.setTextinput(editText.getText().toString());

        canvasView.invalidate();

        editText.setVisibility(View.GONE);
        toggleText.setVisibility(View.GONE);
    }

}
