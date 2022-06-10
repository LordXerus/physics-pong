package lordxerus.physics.main;

import org.dyn4j.geometry.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Camera extends Component {
    // how much t
    public static final float UNITS_WIDTH = 20.0f;

    private final PGraphics graphics;
    private final float viewScale;


    private final Vector2 positionOffset = new Vector2(0f, 0f);
    private float rotationOffset = 0.0f;
    private float scale = 1.0f;

    Camera(PGraphics p) {
        graphics = p;

        viewScale = p.width / UNITS_WIDTH / 2;
    }

    public PGraphics getGraphics() {
        return graphics;
    }

    public final float pixels(float units) {
        return units / viewScale;
    }

    public void render() {
        graphics.beginDraw();
        graphics.rectMode(PConstants.RADIUS);

        graphics.translate(graphics.width / 2f, graphics.height / 2f);
        graphics.scale(viewScale);

        graphics.rotate(-rotationOffset);
        graphics.translate((float) -positionOffset.x, (float) -positionOffset.y);

        graphics.background(0xffcccccc);

        graphics.strokeWeight(pixels(1f));
        graphics.fill(0xffffffff);
        graphics.push();

        graphics.rect(0, 0, 10, 6);


        graphics.pop();
        graphics.endDraw();
    }


    public Vector2 getPosOffset() {
        return positionOffset;
    }
    public void setPositionOffset(float x, float y) {
        positionOffset.set(x, y);
    }

    public float getRotationOffset() {
        return rotationOffset;
    }
    public void setRotationOffset(float theta) {
        rotationOffset = theta;
    }

    public float getScale() {
        return scale;
    }
    public void setScale(float s) {
        scale = s;
    }
}
