package lordxerus.pong2022.rendering;

import lordxerus.pong2022.rendering.renderers.Renderable;
import org.dyn4j.geometry.Vector2;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Camera {

    // how much t
    private static final float UNITS_HEIGHT = 10.0f;

    private final PGraphics graphics;
    public PGraphics getGraphics() {
        return graphics;
    }
    private final float viewScale;

    public class Info implements ScaleInfo {
        private Info() {}
        public float getScale() {
            return Camera.this.viewScale;
        }
        public float px(float unit) {
            return unit / getScale();
        }
    }

    private final Info scaleInfo;

    //<editor-fold desc="Camera Position/Rotation Offset">
    private final Vector2 positionOffset = new Vector2(0f, 0f);
    private float rotationOffset = 0.0f;

    public Vector2 getPositionOffset() {
        return positionOffset;
    }
    public void setPositionOffset(float x, float y) {
        positionOffset.set(x, y);
    }
    public void setPositionOffset(Vector2 v) {
        positionOffset.set(v);
    }
    public void translate(float x, float y) {
        positionOffset.add(x, y);
    }
    public void translate(Vector2 v) {
        positionOffset.add(v.x, v.y);
    }

    public float getRotationOffset() {
        return rotationOffset;
    }
    public void setRotationOffset(float theta) {
        rotationOffset = theta;
    }
    public void rotate(float theta) {
        rotationOffset += theta;
    }
    //</editor-fold>

    //<editor-fold desc="BG Color">
    private int bg_color = 0xffcccccc;
    public int getBG_color() { return bg_color; }
    public void setBG_color(int color) { bg_color = color; }
    //</editor-fold>

    //<editor-fold desc="Masking">
    private int mask = 0xffffffff;

    public int getMask() { return mask; }
    public void setMask(int mask) { this.mask = mask; }
    public void setMaskOr(int mask) { this.mask |= mask; }
    public void setMaskAnd(int mask) { this.mask &= mask; }
    public void setMaskNotAnd(int mask) { this.mask &= ~mask; }
    //</editor-fold>

    public final float getUnitsHeight() {
        return UNITS_HEIGHT * 2;
    }
    private final float aspectRatio; // see constructor
    public final float getAspectRatio() {
        return aspectRatio;
    }
    private final float unitsWidth;
    public final float getUnitsWidth() {
        return unitsWidth * 2;
    }
    private void initialize() {
        graphics.beginDraw();


        graphics.rectMode(PConstants.CENTER);
        graphics.ellipseMode(PConstants.RADIUS);
        graphics.strokeWeight(pixels(1f));
        graphics.endDraw();
    }

    public Camera(PApplet applet, int width, int height, RenderWorld<?> world) {
        graphics = applet.createGraphics(width, height, applet.sketchRenderer());
        renderWorld = world;

        viewScale = graphics.height / UNITS_HEIGHT / 2;
        scaleInfo = new Info();

        aspectRatio = (float)width / height;
        unitsWidth = UNITS_HEIGHT * aspectRatio;

        initialize();
    }

    public Camera(Camera camera) {
        PGraphics og = camera.graphics;
        graphics = og.parent.createGraphics(og.width, og.height);
        renderWorld = camera.renderWorld;

        this.setPositionOffset(camera.positionOffset);
        this.setRotationOffset(camera.rotationOffset);
        this.setBG_color(camera.bg_color);
        this.setMask(camera.mask);

        viewScale = graphics.width / UNITS_HEIGHT / 2;
        scaleInfo = new Info();

        aspectRatio = camera.aspectRatio;
        unitsWidth = camera.unitsWidth;
        initialize();
    }

    RenderWorld<? extends Renderable> renderWorld;
    public RenderWorld<? extends Renderable> getRenderWorld() {
        return renderWorld;
    }
    public void setRenderWorld(RenderWorld<? extends Renderable> world) {
        renderWorld = world;
    }

    public final float pixels(float units) {
        return units / viewScale;
    }

    public void draw() {
        graphics.beginDraw();
        graphics.background(bg_color);
        graphics.fill(0xffffffff);

        graphics.translate(graphics.width / 2f, graphics.height / 2f);
        graphics.scale(viewScale, -viewScale);

        graphics.rotate(-rotationOffset);
        graphics.translate((float) -positionOffset.x, (float) -positionOffset.y);
        graphics.pushMatrix(); // matrix for converting to pixel space


        renderWorld.getRenderers().forEach(g -> {
                if ((mask & g.getCategory()) != 0) {
                    graphics.push();
                    g.render(graphics, scaleInfo);
                    graphics.pop();
                }
            }
        );

        graphics.popMatrix();

        graphics.endDraw();
    }



}
