package at.ac.univie.se2ws2020team0310.sketch_app;

import android.app.Activity;
import android.graphics.Canvas;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Collections;
import java.util.List;

import at.ac.univie.se2ws2020team0310.sketch_app.view.CanvasView;
import at.ac.univie.se2ws2020team0310.sketch_app.view.MainActivity;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.DrawLineStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.view.draw.IDrawStrategy;

public class CanvasViewTest {
    @Test
    public void selectedGraphicalElementTest() {
        String graphicalElement = "Circle";
        String selectedGraphicalElement = graphicalElement;
        List drawnElements = null;
        List drawnElementsAdded = null;

        if(selectedGraphicalElement != null){
            drawnElementsAdded = Collections.singletonList(drawnElements.add(selectedGraphicalElement));
            assertNotEquals(drawnElements,drawnElementsAdded);
        }
    }
    @Test
    public void setXCoordinateTest() {
        Double xCoord = 50.0;
        Double xCoordTest = null;

        if(xCoord != null){
           xCoordTest = xCoord;
           assertNotEquals(xCoordTest,xCoord);
        }
    }

    @Test
    public void setYCoordinateTest() {
        Double yCoord = 40.0;
        Double yCoordTest = null;

        if(yCoord != null){
            yCoordTest = yCoord;
            assertNotEquals(yCoordTest,yCoord);
        }
    }
}
