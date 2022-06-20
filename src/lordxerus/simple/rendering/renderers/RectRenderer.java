package lordxerus.simple.rendering.renderers;

import lordxerus.simple.annotation.NotNullByDefault;
import lordxerus.simple.rendering.ScaleInfo;
import processing.core.PGraphics;

@NotNullByDefault
public class RectRenderer extends AbstractShapeRenderer {

    private float width;
    private float height;

    private boolean usePixelDimensions = false;

    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public void scaleWidth(float factor) {
        this.width *= factor;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public void scaleHeight(float factor) {
        this.height *= factor;
    }

    public void setDimensions(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public boolean isUsingPixelDimensions() {
        return this.usePixelDimensions;
    }
    public void setUsePixelDimensions(boolean pixels) {
        this.usePixelDimensions = pixels;
    }
    protected void drawShape(PGraphics g, ScaleInfo info) {
        if(usePixelDimensions)
            g.rect(0, 0, info.px(width), info.px(height));
        else
            g.rect(0, 0, width, height);
    }
}
