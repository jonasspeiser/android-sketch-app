package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                // TODO

                break;

            case R.id.fingerId:
                // TODO
                break;

            case R.id.lineId:
                // TODO
                break;

            case R.id.circleId:
                canvasView.selectCircle();
                break;

            case R.id.squareId:
                // TODO
                break;

            case R.id.triangleId:
                // TODO
                break;

            case R.id.loadId:
                // TODO
                break;

            case R.id.saveId:
                // TODO
                break;

            case R.id.exportId:
                // TODO
                break;

            case R.id.deleteId:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}