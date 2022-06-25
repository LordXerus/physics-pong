package lordxerus.pong2022.scenes.pongScene;

import lordxerus.pong2022.input.KeyboardInput;
import lordxerus.pong2022.rendering.RenderWorld;
import lordxerus.pong2022.rendering.renderers.RectRenderer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.PrismaticJoint;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class PongPaddle {
    Body base = new Body();
    RectRenderer renderer = new RectRenderer();

    PongPaddle (
            float x, float y, float vertical, float thickness,
            RenderWorld<? super RectRenderer> renderWorld,
            World<Body> physicsWorld, Body anchor, int color
    ) {
        BodyFixture fixture = new BodyFixture(Geometry.createRectangle(thickness, vertical));
        fixture.setDensity(0.1);
        fixture.setRestitution(0.0);
        base.addFixture(
                fixture
        );

        base.setMassType(MassType.NORMAL);
        base.updateMass();
        base.translate(x, y);
        base.setGravityScale(0.0);
        base.setLinearDamping(10.0);

        base.setUserData(this);

        renderer.setTransformReference(base.getTransform());
        renderer.setDimensions(thickness, vertical);

        renderer.setFill(color);

        PrismaticJoint<Body> joint = new PrismaticJoint<>(base, anchor, new Vector2(0, 0), new Vector2(0, 1));

        renderWorld.addRenderer(renderer, -2);
        physicsWorld.addBody(base);
        physicsWorld.addJoint(joint);
    }

    String keyUp;
    String keyDown;
    String keyDamper;

    private boolean lastDamper;
    public void setKeys (String up, String down, String damper) {
        keyUp = up;
        keyDown = down;
        keyDamper = damper;
    }

    public void handleInput(KeyboardInput keyboard) {
        if(keyboard.getInput(keyUp)) {
            base.applyForce(new Vector2(0, 50));
        }
        if(keyboard.getInput(keyDown)) {
            base.applyForce(new Vector2(0, -50));
        }
        if (!lastDamper && keyboard.getInput(keyDamper)) {
            lastDamper = true;
            base.setLinearDamping(0.0);
            base.setBullet(true);
        } else if (lastDamper && !keyboard.getInput(keyDamper)) {
            lastDamper = false;
            base.setLinearDamping(10.0);
            base.setBullet(false);
        }

    }

    public final void reset() {
        base.getTransform().setTranslationY(0);
    }
}