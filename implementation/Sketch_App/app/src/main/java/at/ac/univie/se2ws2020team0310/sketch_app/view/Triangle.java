package at.ac.univie.se2ws2020team0310.sketch_app.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle extends GraphicalElement {

    public void drawTriangle(Canvas canvas, float x, float y, Paint paint) {

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
