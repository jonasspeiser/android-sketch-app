package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Paint;

import org.junit.Test;
import org.mockito.Mock;

import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTextStrategy;

import static org.junit.Assert.assertTrue;

public class TextTest {

    @Mock
    DrawTextStrategy strategy = new DrawTextStrategy();
    Text text = new Text(strategy);
    Paint mPaint = new Paint();


    @Test
    public void testIsWithinElement() {
        text.xPosition = 100;
        text.yPosition = 200;

        float textsize = 150;

        text.setUserText("hallo");
        mPaint.measureText(text.getUserText());

        //mPaint.setTextSize(textsize);
        //mPaint.setTextSize(text.setSize(size));

        text.setSize(textsize);
        mPaint.setTextSize(text.getSize());

        //test passes if touch is within quadrangle
        assertTrue(text.isWithinElement(100,200));

    }
}