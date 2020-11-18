package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle extends GraphicalElement {

// Methods

    public void drawTriangle(Canvas canvas, float x, float y, Paint paint) {
        // in Anlehnung an: https://kylewbanks.com/blog/drawing-triangles-rhombuses-and-other-shapes-on-android-canvas#:~:text=Simply%20call%20drawTriangle%20with%20the,the%20width%20of%20the%20triangle.&text=Not%20bad%2C%20with%20a%20little,for%20your%20triangle%20drawing%20needs.
        float halfWidth = this.getShapeSize() / 2;

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }
}
