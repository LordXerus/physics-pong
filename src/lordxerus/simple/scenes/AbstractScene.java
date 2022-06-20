package lordxerus.simple.scenes;

import lordxerus.simple.input.KeyboardInput;
import lordxerus.simple.rendering.Camera;
import processing.core.PApplet;

public abstract class AbstractScene {
    public static final KeyboardInput Keyboard = new KeyboardInput();
    public static final SceneManager Scenes = new SceneManager();

    private int lastMillis = 0;

    private final PApplet applet;

    public AbstractScene(PApplet applet) {
        this.applet = applet;
    }


    //<editor-fold desc="API for Subclasses">
    protected float random(float low, float high) {
        return applet.random(low, high);
    }
    protected float randomGaussian() {
        return applet.randomGaussian();
    }
    protected void draw(Camera camera, float a, float b) {
        applet.image(camera.getGraphics(), a, b);
    }
    protected void draw(Camera camera, float a, float b, float c, float d) {
        applet.image(camera.getGraphics(), a, b, c, d);
    }
    //</editor-fold>
    protected abstract void setup();

    public final void doTick() {
        int m = applet.millis();
        update((m - lastMillis) / 1000f);

        lastMillis = m;
    }
    protected abstract void update(float dt);





}
