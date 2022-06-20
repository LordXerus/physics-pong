package lordxerus.simple.rendering.renderers;


import lordxerus.simple.annotation.NotNullByDefault;
import lordxerus.simple.rendering.ScaleInfo;
import processing.core.PGraphics;
import processing.core.PShape;

@NotNullByDefault
public class ShapeRenderer extends AbstractShapeRenderer {

    private final PShape shape;

    ShapeRenderer(PShape shape) {
        this.shape = shape;
    }

    @Override
    protected void drawShape(PGraphics graphics, ScaleInfo scaleInfo) {
        graphics.shape(shape);
    }
}
