package lordxerus.pong2022.scenes.pongScene;

import ddf.minim.AudioPlayer;
import lordxerus.pong2022.input.Key;
import lordxerus.pong2022.input.KeyboardInput;
import lordxerus.pong2022.rendering.Camera;
import lordxerus.pong2022.rendering.RenderWorld;
import lordxerus.pong2022.rendering.renderers.AbstractRenderer;
import lordxerus.pong2022.scenes.AbstractScene;
import org.dyn4j.dynamics.Body;
import org.dyn4j.world.World;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.sound.SoundFile;
public class PongScene extends AbstractScene {
    RenderWorld<AbstractRenderer> renderWorld = new RenderWorld<>();

    World<Body> physicsWorld = new World<>();

    final float invAspectRatio;

    private void buildWalls() {
        final float thickness = 6;
        //North
        new PongWall(
                0, mainCamera.getUnitsHeight()/2 + 2f,
                mainCamera.getUnitsWidth() + 2, thickness,
                renderWorld, physicsWorld, 0xffffffff
        );
        //South
        new PongWall(
                0, -mainCamera.getUnitsHeight()/2 - 2f,
                mainCamera.getUnitsWidth() + 2, thickness,
                renderWorld, physicsWorld, 0xffffffff
        );
//        //West
//        new PongWall(
//                -mainCamera.getUnitsWidth()/2, 0,
//                thickness, mainCamera.getUnitsHeight() + 1,
//                renderWorld, physicsWorld, 0xffffffff
//        );
//        //East
//        new PongWall(
//                mainCamera.getUnitsWidth()/2, 0,
//                thickness, mainCamera.getUnitsHeight() + 1,
//                renderWorld, physicsWorld, 0xffffffff
//        );
    }
    PongBall ball1;
    PongBall ball2;
    private void buildBall() {
        String audioName = "wall.mp3";
        boolean audioExists = applet.dataFile(audioName).isFile();

       ball1 = new PongBall(0, 0, 0.5f, renderWorld, physicsWorld, 0xffff0000, 10);
       ball2 = new PongBall(0, 1, 0.5f, renderWorld, physicsWorld, 0xffff0000, 10);

        if(audioExists) {
            ball1.setWallSound(Audio.loadSample("wall.mp3"));
            ball2.setWallSound(Audio.loadSample("wall.mp3"));
        } else {
            System.out.println("./data/" + audioName + " cannot be found. Skipping.");
        }

    }
    PongPaddle leftPaddle;
    PongPaddle rightPaddle;
    private void buildPaddle() {
        PongPaddleAnchor anchor = new PongPaddleAnchor(0, 0, physicsWorld);
        Body anchorBody = anchor.getBody();

        leftPaddle = new PongPaddle(-mainCamera.getUnitsWidth()/2, 0, 4, 0.5f, renderWorld, physicsWorld, anchorBody, 0xffffffff);

        leftPaddle.setKeys("LUp", "LDown", "LSpecial");

        rightPaddle = new PongPaddle(mainCamera.getUnitsWidth()/2, 0, 4, 0.5f, renderWorld, physicsWorld, anchorBody, 0xffffffff);

        rightPaddle.setKeys("RUp", "RDown", "RSpecial");
    }

    public PongScene(PApplet applet) {
        super(applet);

        mainCamera = new Camera(applet, applet.width, applet.height, renderWorld);
        physicsWorld.setGravity(0, 0);

        invAspectRatio = (float) applet.height / applet.width;
        mainCamera.setBG_color(0xff000000);

        buildWalls();
        buildBall();
        buildPaddle();

        Keyboard.setInput("LDown", Key.get('s'));
        Keyboard.setInput("LUp", Key.get('w'));
        Keyboard.setInput("LSpecial", Key.get('a'));

        Keyboard.setInput("RDown", Key.get(PConstants.DOWN));
        Keyboard.setInput("RUp", Key.get(PConstants.UP));
        Keyboard.setInput("RSpecial", Key.get(PConstants.RIGHT));

        Keyboard.setInput("reset", Key.get(' '));
    }


    private final Camera mainCamera;
    @Override
    public void setup() {

    }

    @Override
    public void update(double dt) {

        leftPaddle.handleInput(Keyboard);
        rightPaddle.handleInput(Keyboard);

        if(Keyboard.getInput("reset")) {
            ball1.reset();
            ball2.reset();
            leftPaddle.reset();
            rightPaddle.reset();
        }

        mainCamera.draw();
        physicsWorld.update(dt);
        draw(mainCamera, 0, 0);


    }
}
