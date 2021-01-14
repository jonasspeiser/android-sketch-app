package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.view.Menu;
import android.view.MenuInflater;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.R;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest extends TestCase {

    @Mock
    Menu menu;

    @Mock
    MainActivity mainActivity;

    //Test for Menu inflation
    @Test
    public void onCreateOptionsMenuTest() {
        assertNotNull(mainActivity.onCreateOptionsMenu(menu));
    }
}