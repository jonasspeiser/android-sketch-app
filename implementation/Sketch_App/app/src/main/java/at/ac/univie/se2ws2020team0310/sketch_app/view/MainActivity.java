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
        SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
        SeekBar textSizeSeekBar = findViewById(R.id.textSizeSeekBar);

        // TODO: create method for this
        findViewById(R.id.strokeWidthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBar strokeWidthSeekBar = findViewById(R.id.strokeWidthSeekBar);
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
        textSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar textSizeSeekBar, int progress, boolean fromUser) {
                GraphicalElement.changeTextSize(progress);
            }

            public void onStartTrackingTouch(SeekBar textSizeSeekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar textSizeSeekBar) {
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
                SeekBar textSizeSeekBar = findViewById(R.id.textSizeSeekBar);
                if(textSizeSeekBar.getVisibility()==SeekBar.VISIBLE){
                    textSizeSeekBar.setVisibility(View.INVISIBLE);
                }else{
                    textSizeSeekBar.setVisibility(View.VISIBLE);
                }
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
}
