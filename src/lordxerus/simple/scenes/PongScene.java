package lordxerus.simple.scenes;

import lordxerus.simple.rendering.Camera;
import lordxerus.simple.rendering.RenderWorld;
import lordxerus.simple.rendering.renderers.AbstractRenderer;
import lordxerus.simple.rendering.renderers.CircleRenderer;
import lordxerus.simple.rendering.renderers.RectRenderer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.world.World;
import processing.core.PApplet;

public class PongScene extends AbstractScene {
    RenderWorld<AbstractRenderer> renderWorld = new RenderWorld<>();

    World<Body> physicsWorld = new World<>();

    final float invAspectRatio;

    private void buildWalls() {
        //<editor-fold desc="North">
        BodyFixture northFixture = new BodyFixture(
                Geometry.createRectangle(mainCamera.getUnitsWidth() * 2 + 2, 1)
        );

        Body northBody = new Body();
        northBody.setMass(MassType.INFINITE);
        northBody.addFixture(northFixture);
        northBody.updateMass();

        RectRenderer northRender = new RectRenderer();
        northRender.setTransformReference(northBody.getTransform());
        northRender.setDimensions(mainCamera.getUnitsWidth() * 2 + 2, 1);

        northBody.translate(0, mainCamera.getUnitsHeight());

        northRender.setFill(0xffffffff);
        renderWorld.addRenderer(northRender, 0);
        physicsWorld.addBody(northBody);
        //</editor-fold>

        //<editor-fold desc="South">
        BodyFixture southFixture = new BodyFixture(
                Geometry.createRectangle(mainCamera.getUnitsWidth() * 2 + 2, 1)
        );

        Body southBody = new Body();
        southBody.setMassType(MassType.INFINITE);
        southBody.addFixture(southFixture);
        southBody.updateMass();

        RectRenderer southRenderer = new RectRenderer();
        southRenderer.setTransformReference(southBody.getTransform());
        southRenderer.setDimensions(mainCamera.getUnitsWidth() * 2 + 2, 1);

        southBody.translate(0, -mainCamera.getUnitsHeight());

        southRenderer.setFill(0xFFFFFFFF);
        renderWorld.addRenderer(southRenderer, 0);
        physicsWorld.addBody(southBody);
        //</editor-fold>

        //<editor-fold desc="West">
        BodyFixture westFixture = new BodyFixture(
                Geometry.createRectangle(1, mainCamera.getUnitsHeight() * 2 + 2)
        );

        Body westBody = new Body();
        westBody.setMassType(MassType.INFINITE);
        westBody.addFixture(westFixture);
        westBody.updateMass();

        RectRenderer westRenderer = new RectRenderer();
        westRenderer.setTransformReference(westBody.getTransform());
        westRenderer.setDimensions(1, mainCamera.getUnitsHeight() * 2 + 2);

        westBody.translate(-mainCamera.getUnitsWidth(), 0);

        westRenderer.setFill(0xffffffff);
        renderWorld.addRenderer(westRenderer, 0);
        physicsWorld.addBody(westBody);
        //</editor-fold>

        //<editor-fold desc="East">
        BodyFixture eastFixture = new BodyFixture(
                Geometry.createRectangle(1, mainCamera.getUnitsHeight() * 2 + 2)
        );

        Body eastBody = new Body();
        eastBody.setMassType(MassType.INFINITE);
        eastBody.addFixture(eastFixture);
        eastBody.updateMass();


        RectRenderer eastRenderer = new RectRenderer();
        eastRenderer.setTransformReference(eastBody.getTransform());
        eastRenderer.setDimensions(1, mainCamera.getUnitsHeight() * 2 + 2);

        eastBody.translate(mainCamera.getUnitsWidth(), 0);

        eastRenderer.setFill(0xffffffff);
        renderWorld.addRenderer(eastRenderer, 0);
        physicsWorld.addBody(eastBody);
        //</editor-fold>
    }
    private void buildBall() {
        BodyFixture ballFixture = new BodyFixture(
                Geometry.createCircle(1.0f)
        );
        ballFixture.setRestitutionVelocity(0.1f);
        ballFixture.setRestitution(1.0f);
        ballFixture.setFriction(0.0f);

        Body ballBody = new Body();
        ballBody.setMassType(MassType.NORMAL);
        ballBody.addFixture(ballFixture);
        ballBody.updateMass();

        ballBody.setLinearVelocity(25, 25);

        CircleRenderer ballRenderer = new CircleRenderer();
        ballRenderer.setTransformReference(ballBody.getTransform());
        ballRenderer.setRadius((float) 1.0);

        ballRenderer.setFill(0xffff0000);
        renderWorld.addRenderer(ballRenderer, 1);
        physicsWorld.addBody(ballBody);

    }

    public PongScene(PApplet applet) {
        super(applet);

        mainCamera = new Camera(applet, applet.width, applet.height, renderWorld);
        physicsWorld.setGravity(0, 0);

        invAspectRatio = (float) applet.height / applet.width;
        mainCamera.setBG_color(0xff000000);

        buildWalls();
        buildBall();
    }


    private final Camera mainCamera;
    @Override
    public void setup() {

    }

    @Override
    public void update(float dt) {
        mainCamera.draw();
        physicsWorld.update(dt);
        draw(mainCamera, 0, 0);


    }
}
