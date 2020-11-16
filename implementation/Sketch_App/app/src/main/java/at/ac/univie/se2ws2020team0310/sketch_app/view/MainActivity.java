package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

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
        canvasView = (CanvasView) findViewById(R.id.canvasView);
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
                if(textSizeSeekBar.VISIBLE == 1){
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