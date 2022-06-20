package lordxerus.simple.rendering;

import lordxerus.simple.annotation.NotNullByDefault;
import lordxerus.simple.rendering.renderers.Renderable;

import java.util.*;
import java.util.stream.Stream;

@NotNullByDefault
public class RenderWorld <T extends Renderable> {

    private final Map<Integer, Set<T>> renderers = new HashMap<>();
    private final TreeSet<Integer> priorities = new TreeSet<>();
    public Stream<T> getRenderers() {
        return priorities.stream()
                .map(renderers::get)
                .flatMap(Set::stream);

    }

    public void addRenderer(T renderer, int priority) {
        Set<T> bucket = renderers.computeIfAbsent(priority, k -> {
            priorities.add(priority);
            return new HashSet<>();
        });

        if(!bucket.add(renderer)) throw new IllegalArgumentException("Renderer already in world.");
        bucket.add(renderer);

    }
    public void removeRenderer(T renderer) {
        for(Set<T> bucket : renderers.values()) {
            if(bucket.remove(renderer)) return;
        }
        throw new IllegalArgumentException("Renderer not in world");
    }
}
