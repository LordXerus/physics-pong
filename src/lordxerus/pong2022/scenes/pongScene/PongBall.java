package lordxerus.pong2022.scenes.pongScene;

import ddf.minim.AudioSample;
import lordxerus.pong2022.rendering.RenderWorld;
import lordxerus.pong2022.rendering.renderers.CircleRenderer;
import org.dyn4j.collision.CollisionBody;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Transform;
import org.dyn4j.world.BroadphaseCollisionData;
import org.dyn4j.world.ManifoldCollisionData;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.CollisionListener;
import processing.sound.SoundFile;

class PongBall implements PongTagged {

    Body body = new Body();
    CircleRenderer renderer = new CircleRenderer();

    boolean isMoving;

    public boolean isMoving() {
        return isMoving;
    }
    public void setMoving(boolean moving) {
        isMoving = moving;
        if(moving) {
            
        // TODO: handle game beginning. Scene Switching.
        }
    }

    private AudioSample wallSound;
    public AudioSample getWallSound() {
        return wallSound;
    }
    public void setWallSound(AudioSample sound) {
        wallSound = sound;
    }


    private class BallCollisionListener implements CollisionListener<Body, BodyFixture> {

        @Override
        public boolean collision(BroadphaseCollisionData<Body, BodyFixture> collision) {
            return true;
        }

        @Override
        public boolean collision(NarrowphaseCollisionData<Body, BodyFixture> collision) {



            return true;
        }

        @Override
        public boolean collision(ManifoldCollisionData<Body, BodyFixture> collision) {
            CollisionBody<BodyFixture> other;
            if (collision.getBody1() == body) {
                other = collision.getBody2();
            } else if (collision.getBody2() == body) {
                other = collision.getBody1();
            } else return true;

            try {
                String otherTag = ((PongTagged) other.getUserData()).getTag();

                if(wallSound != null && otherTag.equals("Wall")) {
                    wallSound.trigger();
                }

            } catch (ClassCastException ignored){}
            return true;
        }
    }

    private final Transform initialTransform;
    private final Transform initialVelocity = new Transform();


    PongBall(
            float x, float y, float radius,
            RenderWorld<? super CircleRenderer> renderWorld,
            World<Body> physicsWorld, int color,

            float baseVelocity
    ) {
        BodyFixture fixture = new BodyFixture(Geometry.createCircle(radius));
        fixture.setRestitution(1.0);
        fixture.setRestitutionVelocity(0.1);
        fixture.setFriction(0.0);


        body.addFixture(fixture);


        body.setMassType(MassType.NORMAL);
        body.updateMass();
        body.translate(x, y);
        body.setLinearVelocity(baseVelocity, baseVelocity);

        body.setUserData(this);

        renderer.setTransformReference(body.getTransform());
        renderer.setRadius(radius);

        renderer.setFill(color);

        initialTransform = body.getTransform().copy();
        initialVelocity.setTranslation(body.getLinearVelocity());
        initialVelocity.setRotation(body.getAngularVelocity());


        renderWorld.addRenderer(renderer, -2);
        physicsWorld.addBody(body);
        physicsWorld.addCollisionListener(new BallCollisionListener());
    }

    public final void reset() {
        body.setTransform(initialTransform);
        body.getPreviousTransform().set(initialTransform);

        body.setLinearVelocity(initialVelocity.getTranslation());
        body.setAngularVelocity(initialVelocity.getRotationAngle());
    }

    public String getTag() {
        return "Ball";
    }
}
