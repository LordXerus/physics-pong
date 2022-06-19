package lordxerus.simple.rendering.renderers;

import lordxerus.simple.rendering.ScaleInfo;
import processing.core.PGraphics;

public interface Renderable {
    void render(PGraphics graphics, ScaleInfo scaleInfo);
    int getCategory();
}
