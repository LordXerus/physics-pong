package lordxerus.pong2022.scenes;

import java.util.*;

public class SceneManager {
    private final Deque<AbstractScene> scenes = new ArrayDeque<>();


    public void pushScene(AbstractScene scene) {
        scenes.addFirst(scene);
        scene.setup();
    }
    public void popScene(AbstractScene scene) {
        if (scenes.getLast() != scene) throw new IllegalArgumentException("A scene can only pop itself.");
        scenes.removeLast();
    }
    public Iterable<AbstractScene> getScenes() {
        return scenes;
    }
}
