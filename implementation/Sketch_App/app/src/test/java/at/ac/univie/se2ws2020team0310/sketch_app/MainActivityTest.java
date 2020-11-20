package at.ac.univie.se2ws2020team0310.sketch_app;

import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import static org.junit.Assert.assertEquals;

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

    public void testOnClickListener(){
        findViewById(R.id.sizeButton).setOnClickListener(v -> {
            sizeSeekBar.setVisibility(SeekBar.INVISIBLE);
            if (sizeSeekBar.getVisibility() == SeekBar.VISIBLE) {
                sizeSeekBar.setVisibility(View.INVISIBLE);
                assertEquals("Visibility is incorrect",sizeSeekBar.getVisibility(),View.INVISIBLE);
            } else {
                if (canvasView.getAppViewModel().getSelectedGraphicalElement() == null) {
                    Toast error = Toast.makeText(getApplicationContext(), "No graphical element selected", Toast.LENGTH_LONG);
                    error.show();
                    assertEquals("SeekBar visibility state is incorrect",sizeSeekBar.getVisibility(),View.INVISIBLE);
                }
                if (canvasView.getAppViewModel().getSelectedGraphicalElement() != null) {
                    sizeSeekBar.setVisibility(View.VISIBLE);
                    assertEquals("SeekBar visibility state is incorrect",sizeSeekBar.getVisibility(),View.VISIBLE);
                }
            }
        });
    }
}
