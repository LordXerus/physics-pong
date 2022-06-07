package lordxerus.physics.main;

import org.dyn4j.geometry.Vector2;
import processing.core.PGraphics;

public class Camera {
    // how much t
    public static final float UNITS_WIDTH = 10.0f;

    private final PGraphics graphics;
    private final float viewScale;


    private Vector2 positionOffset = new Vector2(0f, 0f);
    private float rotationOffset = 0.0f;
    private float scale = 1.0f;


    float tempTranslateX = 0, tempTranslateY = 0;

    Camera(PGraphics p) {
        graphics = p;
        viewScale = UNITS_WIDTH / p.width;
    }

    public PGraphics getGraphics() {
        return graphics;
    }

    public void render() {

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
