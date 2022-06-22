package lordxerus.pong2022.scenes;

import ddf.minim.Minim;
import lordxerus.pong2022.input.KeyboardInput;
import lordxerus.pong2022.rendering.Camera;
import processing.core.PApplet;

public abstract class AbstractScene {
    public static final KeyboardInput Keyboard = new KeyboardInput();
    public static final SceneManager Scenes = new SceneManager();

    public final Minim Audio;

    private int lastMillis = 0;

    private final PApplet applet;

    public AbstractScene(PApplet applet) {

        this.applet = applet;
        Audio = new Minim(applet);


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
        update((m - lastMillis) / 1000.);

        lastMillis = m;
    }
    protected abstract void update(double dt);





}
