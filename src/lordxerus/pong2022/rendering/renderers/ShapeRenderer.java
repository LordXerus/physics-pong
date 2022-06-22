package lordxerus.pong2022.rendering.renderers;


import lordxerus.pong2022.annotation.NotNullByDefault;
import lordxerus.pong2022.rendering.ScaleInfo;
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
