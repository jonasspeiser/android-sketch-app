package at.ac.univie.se2ws2020team0310.sketch_app.model.draw;

import android.graphics.Canvas;
import android.graphics.Path;

import at.ac.univie.se2ws2020team0310.sketch_app.model.graphicalElements.GraphicalElement;

public class DrawTriangleStrategy implements DrawStrategy {
    @Override
    public void draw(Canvas canvas, GraphicalElement graphicalElement) {
        // in Anlehnung an: https://kylewbanks.com/blog/drawing-triangles-rhombuses-and-other-shapes-on-android-canvas#:~:text=Simply%20call%20drawTriangle%20with%20the,the%20width%20of%20the%20triangle.&text=Not%20bad%2C%20with%20a%20little,for%20your%20triangle%20drawing%20needs.
        float halfWidth = graphicalElement.getSize() / 2;
        float x = graphicalElement.getXPosition();
        float y = graphicalElement.getYPosition();


        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, graphicalElement.getObjectPaint());
    }
}
