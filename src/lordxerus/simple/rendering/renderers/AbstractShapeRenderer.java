package lordxerus.simple.rendering.renderers;

import lordxerus.simple.annotation.NotNullByDefault;
import lordxerus.simple.rendering.ScaleInfo;
import processing.core.PGraphics;

@NotNullByDefault
abstract class AbstractShapeRenderer extends AbstractRenderer {


    //<editor-fold desc="Stroke Settings">
    private boolean useStroke = true;
    private float strokeWeight = 1;
    private int stroke = 0xff000000;
    protected boolean usePixelStroke = true;

    public final int getStroke() {
        return stroke;
    }
    public final float getStrokeWeight() {
        return strokeWeight;
    }
    public final boolean isUsingStroke() {
        return useStroke;
    }
    public final boolean isUsingPixelStroke() {
        return usePixelStroke;
    }

    public final void setStroke(int color) {
        this.useStroke = true;
        this.stroke = color;
    }
    public final void setStrokeWeight(float weight) {
        this.useStroke = true;
        this.strokeWeight = weight;
    }
    public final void noStroke() {
        this.useStroke = false;
    }
    public final void usePixelsForUnits(boolean pixels) {
        this.usePixelStroke = pixels;
    }
    //</editor-fold>

    //<editor-fold desc="Fill Settings">
    private int fill = 0xffffffff;
    private boolean useFill = true;

    public int getFill() {
        return fill;
    }
    public boolean isUsingFill() {
        return useFill;
    }
    public void setFill(int color) {
        this.useFill = true;
        this.fill = color;
    }
    public void noFill() {
        useFill = false;
    }
    //</editor-fold>

    @Override
    protected final void draw(PGraphics graphics, ScaleInfo scaleInfo) {
        if(useFill)
            graphics.fill(fill);
        else
            graphics.noFill();

        if(useStroke) {
            if(usePixelStroke)
                graphics.strokeWeight(scaleInfo.px(this.strokeWeight));
            else
                graphics.strokeWeight(this.strokeWeight);

            graphics.stroke(this.stroke);
        }
        else graphics.noStroke();

        drawShape(graphics, scaleInfo);

    }

    abstract protected void drawShape(PGraphics graphics, ScaleInfo scaleInfo);
}
