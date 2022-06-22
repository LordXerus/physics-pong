package lordxerus.pong2022.scenes.pongScene;

import lordxerus.pong2022.rendering.RenderWorld;
import lordxerus.pong2022.rendering.renderers.AbstractRenderer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.world.World;

public class PongPaddleAnchor implements PongTagged{
    Body body = new Body();

    PongPaddleAnchor(float x, float y, World<Body> physicsWorld) {
        body.setMassType(MassType.INFINITE);
        body.updateMass();
        body.translate(x, y);
        body.setUserData(this);

        physicsWorld.addBody(body);
    }

    public Body getBody() {
        return body;
    }

    @Override
    public String getTag() {
        return "Anchor";
    }

}
