package at.ac.univie.se2ws2020team0310.sketch_app;

import android.app.Activity;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import at.ac.univie.se2ws2020team0310.sketch_app.model.GraphicalElement;
import at.ac.univie.se2ws2020team0310.sketch_app.view.CanvasView;
import at.ac.univie.se2ws2020team0310.sketch_app.view.MainActivity;

//public class ExampleUnitTest {
    //@Test
    //public void addition_isCorrect() {
    //    assertEquals(4, 2 + 2);
    //}
//}

public class MainActivityTest extends MainActivity{
    private CanvasView canvasView;
    public SeekBar sizeSeekBar;

    public void OnClickListener(){
        findViewById(R.id.sizeButton).setOnClickListener(v -> {
            sizeSeekBar.setVisibility(SeekBar.INVISIBLE);
            if (sizeSeekBar.getVisibility() == SeekBar.VISIBLE) {
                sizeSeekBar.setVisibility(View.INVISIBLE);
                assertEquals("Visibility is incorrect",sizeSeekBar.getVisibility(),View.INVISIBLE);
            } else {
                if (canvasView.getSelectedGraphicalElement() == null) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                    assertEquals("SeekBar visibility state is incorrect",sizeSeekBar.getVisibility(),View.INVISIBLE);
                }
                if (canvasView.getSelectedGraphicalElement() != null) {
                    sizeSeekBar.setVisibility(View.VISIBLE);
                    assertEquals("SeekBar visibility state is incorrect",sizeSeekBar.getVisibility(),View.VISIBLE);
                }
            }
        });

        public void testOnClickListener() {
            int Visibility = setOnCli
        }
    }

}
