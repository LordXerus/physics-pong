package lordxerus.pong2022.rendering.renderers;

import lordxerus.pong2022.annotation.NotNullByDefault;
import lordxerus.pong2022.rendering.ScaleInfo;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;
import processing.core.PGraphics;

import javax.annotation.Nullable;

@NotNullByDefault
public abstract class AbstractRenderer implements Renderable {

    //<editor-fold desc="Category">
    int category = 0x00000001;
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) { this.category = category; }
    public void setCategoryOr(int category) { this.category |= category; }
    public void setCategoryAnd(int category) { this.category &= category; }
    public void setCategoryNotAnd(int category) { this.category &= ~category; }
    //</editor-fold>

    //<editor-fold desc="Camera Position/Rotation Offset">
    private final Vector2 positionOffset = new Vector2(0f, 0f);
    private float rotationOffset = 0.0f;
    private float scale = 1.0f;

    public final Vector2 getPosOffset() {
        return positionOffset;
    }
    public final void setPositionOffset(float x, float y) {
        positionOffset.set(x, y);
    }
    public final void setPositionOffset(Vector2 v) {
        positionOffset.set(v);
    }
    public final void translate(float x, float y) {
        positionOffset.add(x, y);
    }
    public final void translate(Vector2 v) {
        positionOffset.add(v);
    }

    public final float getRotationOffset() {
        return rotationOffset;
    }
    public final void setRotationOffset(float theta) {
        rotationOffset = theta;
    }
    public final void rotate(float theta) {
        rotationOffset += theta;
    }

    public final float getScale() {
        return scale;
    }
    public final void setScale(float s) {
        scale = s;
    }

    @Nullable private Transform transformReference = null;
    @Nullable public final Transform getTransformReference() {
        return transformReference;
    }
    public void setTransformReference(@Nullable Transform reference) {
        transformReference = reference;
    }
    //</editor-fold>

    private boolean activated = true;
    public boolean getActivated() {
        return activated;
    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    public final void render(PGraphics g, ScaleInfo info) {
        if(!activated) return;

        if(transformReference != null) {
            g.translate((float) transformReference.getTranslationX(), (float) transformReference.getTranslationY());
            g.rotate((float) transformReference.getRotationAngle());
        } else {
            g.translate((float) positionOffset.x, (float) positionOffset.y);
            g.rotate(rotationOffset);
        }

        g.scale(scale);

        draw(g, info);

    }

    protected abstract void draw(PGraphics graphics, ScaleInfo scaleInfo);
}
