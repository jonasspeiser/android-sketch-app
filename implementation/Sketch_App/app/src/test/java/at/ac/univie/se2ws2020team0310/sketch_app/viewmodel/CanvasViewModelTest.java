package at.ac.univie.se2ws2020team0310.sketch_app.viewmodel;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import at.ac.univie.se2ws2020team0310.sketch_app.model.Sketch;
import at.ac.univie.se2ws2020team0310.sketch_app.model.observerPatterInterfaces.CustomObserver;

@RunWith(MockitoJUnitRunner.class)
public class CanvasViewModelTest extends TestCase {

    @Mock
    ArrayList<CustomObserver> observers;
    @Mock
    CustomObserver observer;
    @Mock
    CanvasViewModel canvasViewModel;
    @Mock
    Sketch sketch;

    //Test on functionality of observer adding to list of observers
    @Test
    public void testRegisterObserver() {
        canvasViewModel.registerObserver(observer);
        assertFalse(observers.isEmpty());
    }

//    //Test on Coordinate change function
//    @Test
//    public void testElementCoordinateChange() {
//        canvasViewModel.setElementCoordinates((float) 0.43,(float) 0.5);
//        canvasViewModel.changeElementCoordinates((float) 0.44,(float) 0.4,(float) 0.5,(float) 0.6);
//        assertEquals(sketch)
//    }

}