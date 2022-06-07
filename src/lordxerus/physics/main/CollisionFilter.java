package lordxerus.physics.main;

import org.dyn4j.collision.TypeFilter;

public final class CollisionFilter {
    private static class _IAny extends TypeFilter {}

    // Graphics colliders; used for detecting if an object is visible or not
    // All fixtures should be sensors and have no mass (zero density)
    private static class _IGraphics extends _IAny {}

    // Any for all physics-related purposes (non graphics)
    private static class Any extends _IAny {}

    public static final TypeFilter ANY = new Any();
    public static final TypeFilter _IGRAPHICS = new _IGraphics();
}
