package at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import at.ac.univie.se2ws2020team0310.sketch_app.model.decorators.DrawStrategyDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.decorators.ItalicDecorator;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.DrawTextStrategy;
import at.ac.univie.se2ws2020team0310.sketch_app.model.draw.IDrawStrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class TextTest {


//    DrawTextStrategy strategy = new DrawTextStrategy();
//    Text text = new Text(strategy);
//
//    @Test
//    public void testIsWithinElement() {
//        text.xPosition = 100;
//        text.yPosition = 200;
//
//        float textsize = 150;
//
//        text.setUserText("hallo");
//        mPaint.measureText(text.getUserText());
//
//        text.setSize(textsize);
//        mPaint.setTextSize(text.getSize());
//
//        //test passes if touch is within text
//        assertFalse(text.isWithinElement(500,500));
//    }

//    @Test
//    public void italicDecoratorTest() {
//
//
//        IDrawStrategy strategy = new DrawTextStrategy();
//        GraphicalElement text = new Text(strategy);
//
//
//        ItalicDecorator()
//        //IDrawStrategy strategy = new DrawTextStrategy();
//        //GraphicalElement text = new Text();
//        //DrawStrategyDecorator drawStrategyDecorator = new ItalicDecorator(strategy);
//        //assertEquals(0,drawStrategyDecorator.initializePaint(text).getTypeface().getStyle());
//    }

}