package lordxerus.pong2022.scenes.pongScene;

import lordxerus.pong2022.rendering.RenderWorld;
import lordxerus.pong2022.rendering.renderers.RectRenderer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.world.World;

class PongWall implements PongTagged {

    final Body body = new Body();
    final RectRenderer renderer = new RectRenderer();

    PongWall(float x, float y, float width, float height,
             RenderWorld<? super RectRenderer> renderWorld,
             World<? super Body> physicsWorld, int color) {

        body.addFixture(
                Geometry.createRectangle(width, height)
        );
        body.setMassType(MassType.INFINITE);
        body.updateMass();
        body.translate(x, y);

        body.setUserData(this);

        renderer.setTransformReference(body.getTransform());
        renderer.setDimensions(width, height);

        renderer.setFill(color);
        renderer.noStroke();

        renderWorld.addRenderer(renderer, 0);
        physicsWorld.addBody(body);
    }

    @Override
    public String getTag() {
        return "Wall";
    }
}
