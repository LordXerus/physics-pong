package lordxerus.physics.main;

import org.dyn4j.dynamics.Body;
import org.dyn4j.world.World;
import processing.core.PGraphics;

public class PongGame {
    private final World<Body> world = new World<>();

    private final PGraphics graphics;

    private final float physWidth, physHeight;


    PongGame(PGraphics g) {
        this.graphics = g;

        this.physWidth = 10f;
        this.physHeight = physWidth * graphics.height / graphics.width;

    }




}
