package lordxerus.simple.rendering.renderers;

import lordxerus.simple.annotation.NotNullByDefault;
import lordxerus.simple.rendering.ScaleInfo;
import processing.core.PGraphics;

@NotNullByDefault
public final class CircleRenderer extends AbstractShapeRenderer {

    private float radius = 0;
    private boolean usePixels = false;

    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
    public void scaleRadius(float factor) {
        this.radius *= factor;
    }
    public boolean isUsingPixelsRadius() {
        return usePixels;
    }
    public void setUsingPixelsRadius(boolean pixels) {
        this.usePixels = pixels;
    }

    @Override
    protected void drawShape(PGraphics g, ScaleInfo info) {
        g.circle(0, 0, usePixels ? info.px(radius) : radius);
    }

}
