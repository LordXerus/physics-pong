package lordxerus.simple.rendering;

/*@NotNullByDefault
public class BuggedCamera {
    // how much t
    public static final float UNITS_WIDTH = 20.0f;

    private final PGraphics graphics;
    public PGraphics getGraphics() {
        return graphics;
    }
    private final float viewScale;

    //<editor-fold desc="Camera Position/Rotation Offset">
    private final Vector2 positionOffset = new Vector2(0f, 0f);
    private float rotationOffset = 0.0f;

    private void beginTransform() {
        graphics.popMatrix();
    }
    private void endTransform() {
        graphics.pushMatrix();
    }

    public Vector2 getPositionOffset() {
        return positionOffset;
    }
    public void setPositionOffset(float x, float y) {
        beginTransform();
        graphics.translate(
                (float) (positionOffset.x - x),
                (float) (positionOffset.y - y)
        );
        endTransform();
        positionOffset.set(x, y);
    }
    public void setPositionOffset(Vector2 v) {
        beginTransform();
        graphics.translate(
                (float) (positionOffset.x - v.x),
                (float) (positionOffset.y - v.y)
        );
        endTransform();

        positionOffset.set(v);
    }
    public void translate(float x, float y) {
        beginTransform();
        graphics.translate(-x, -y);
        endTransform();

        positionOffset.add(x, y);
    }
    public void translate(Vector2 v) {
        beginTransform();
        graphics.translate((float) -v.x, (float) -v.y);
        endTransform();

        positionOffset.add(v.x, v.y);
    }

    public float getRotationOffset() {
        return rotationOffset;
    }

    private void unsetTranslation() {
        graphics.translate((float) positionOffset.x, (float) positionOffset.y);
    }
    private void setTranslation() {
        graphics.translate((float) -positionOffset.x, (float) -positionOffset.y);
    }
    public void setRotationOffset(float theta) {
        // inverted camera rotation
        beginTransform();
        unsetTranslation();
        graphics.rotate(rotationOffset - theta);
        setTranslation();
        endTransform();

        rotationOffset = theta;
    }
    public void rotate(float theta) {
        beginTransform();
        unsetTranslation();
        graphics.rotate(theta);
        setTranslation();
        endTransform();

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

    private void initialize() {
        graphics.beginDraw();
        graphics.translate(graphics.width / 2f, graphics.height / 2f);
        graphics.scale(viewScale);
        graphics.pushMatrix(); // matrix for converting to pixel space

        graphics.rectMode(PConstants.RADIUS);
        graphics.strokeWeight(pixels(1f));

        graphics.pushMatrix(); // matrix for camera offset, default zero
        graphics.endDraw();
    }

    BuggedCamera(PApplet applet, int width, int height, RenderWorld<?> world) {
        graphics = applet.createGraphics(width, height);
        renderWorld = world;

        viewScale = graphics.width / UNITS_WIDTH / 2;

        initialize();
    }

    BuggedCamera(BuggedCamera camera) {
        PGraphics og = camera.graphics;
        graphics = og.parent.createGraphics(og.width, og.height);
        renderWorld = camera.renderWorld;

        this.setPositionOffset(camera.positionOffset);
        this.setRotationOffset(camera.rotationOffset);
        this.setBG_color(camera.bg_color);
        this.setMask(camera.mask);

        viewScale = graphics.width / UNITS_WIDTH / 2;
        initialize();
    }

    RenderWorld<?> renderWorld;
    public RenderWorld<?> getRenderWorld() {
        return renderWorld;
    }
    public void setRenderWorld(RenderWorld<?> world) {
        renderWorld = world;
    }

    public final float pixels(float units) {
        return units / viewScale;
    }

    public void draw() {
        graphics.beginDraw();
        graphics.background(bg_color);
        graphics.fill(0xffffffff);


        for(Renderable g : renderWorld.getRenderers())
            // if there's at least something in common
            if((mask & g.getCategory()) != 0) {
                graphics.push();
                g.render(graphics, null); /////////////////////// no null allowed?
                graphics.pop();
            }

        graphics.popMatrix();
        graphics.popMatrix();

        graphics.endDraw();
    }



}*/
