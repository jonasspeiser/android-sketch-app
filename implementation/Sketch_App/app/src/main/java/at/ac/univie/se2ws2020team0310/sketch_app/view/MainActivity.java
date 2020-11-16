package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import at.ac.univie.se2ws2020team0310.sketch_app.R;

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

        canvasView = (CanvasView) findViewById(R.id.canvasView);
            strokeWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int strokeWidth = 0;

                public void onProgressChanged(SeekBar textSizeSeekBar, int progress, boolean fromUser) {
                    strokeWidth = progress;
                    Shape.setStrokeWidth(strokeWidth);
                }

                public void onStartTrackingTouch(SeekBar textSizeSeekBar) {
                    // TODO Auto-generated method stub
                }

                public void onStopTrackingTouch(SeekBar textSizeSeekBar) {
                }
            });
        textSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int textSize = 0;

            public void onProgressChanged(SeekBar textSizeSeekBar, int progress, boolean fromUser) {
                textSize = progress;
                Shape.setTextSize(textSize);
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
                // TODO
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