package lordxerus.physics.main;

import lordxerus.physics.engine.annotation.NotNullByDefault;

import javax.annotation.Nullable;

@NotNullByDefault
public abstract class Component {

    @Nullable private GameObject gameObject;
    private boolean activated = false;
    private int state = 2; // 0 = normal, 1 = not yet started, 2 = not yet awakened

    final void attach(GameObject.ComponentSecret secret) {
        if (gameObject != null) throw new IllegalStateException("Already Attached");
        gameObject = secret.getGameObject();
    }
    final void detach(GameObject.ComponentSecret secret) {
        if(secret.getGameObject() == this.gameObject) {
            this.gameObject = null;
            state = 2;
            return;
        }
        throw new IllegalArgumentException("Not correct secret or no object attached");
    }

    final void doTick(float dt) {
        // not yet awakened
        if (state == 2) {
            state = 1;
            awake();
        }

        if (getActivated()) {
            // not yet started
            if (state == 1) {
                state = 0;
                start();
            }
            update(dt);
        }

    }

    public final boolean getActivated() {
        return activated;
    }
    public final void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Nullable protected final GameObject getGameObject() {
        return gameObject;
    }
    @Nullable protected final Scene getScene() {
        if(gameObject == null) return null;
        return gameObject.getScene();
    }

    public void awake(){}
    public void start(){}
    public void update(float dt){}


}
