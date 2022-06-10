package lordxerus.physics.main;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Transform;
import processing.data.IntList;

import java.util.ArrayList;

public class GameObject {

    private final Body body = new Body();
    private Scene scene;

    private final ArrayList<Component> components = new ArrayList<>();

    public final Transform getTransform() {
        return body.getTransform();
    }
    public final Scene getScene() {
        return scene;
    }

    // wow
    // based on https://stackoverflow.com/a/18634125
    public final class ComponentSecret {
        private ComponentSecret() {}
        public GameObject getGameObject() { return GameObject.this; }
    }
    private final ComponentSecret componentSecret = new ComponentSecret();
    public void attachComponent(Component component) {
        components.add(component);
        component.attach(componentSecret);
    }
    public void destroyComponent(Component component) {
        if(components.remove(component))
            component.detach(componentSecret);
    }

    void doTick(float dt) {
        for (Component component : components) {
            component.doTick(dt);
        }
    }


}
