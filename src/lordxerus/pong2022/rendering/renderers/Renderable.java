package lordxerus.pong2022.rendering.renderers;

import lordxerus.pong2022.rendering.ScaleInfo;
import processing.core.PGraphics;

public interface Renderable {
    void render(PGraphics graphics, ScaleInfo scaleInfo);
    int getCategory();
}
