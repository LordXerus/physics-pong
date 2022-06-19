package lordxerus.simple.rendering;

import lordxerus.simple.annotation.NotNullByDefault;
import lordxerus.simple.rendering.renderers.Renderable;

import java.util.*;
import java.util.stream.Stream;

@NotNullByDefault
public class RenderWorld <T extends Renderable> {

    private class Holder {
        public int priority;
        public T renderer;

        public Holder(T renderer, int priority) {
            this.renderer = renderer;
            this.priority = priority;
        }
    }

    private final Set<Holder> renderers = new TreeSet<>(Comparator.comparingInt(holder -> holder.priority));

    public Stream<T> getRenderers() {
        return renderers.stream().map(holder -> holder.renderer);
    }

    public void addRenderer(T renderer, int priority) {
        renderers.add(new Holder(renderer, priority));

    }
    public void removeRenderer(T renderer) {
        renderers.removeIf(holder -> holder.renderer == renderer);
    }
}
