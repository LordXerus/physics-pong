package lordxerus.simple.input;

import processing.core.PConstants;

import java.util.Objects;

public class Key {
    public final boolean coded;
    public final int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return coded == key.coded && value == key.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coded, value);
    }

    private Key(boolean coded, int value){
        this.coded = coded;
        this.value = value;
    }
    public static Key get(char c) {
        return new Key(false, c);
    }
    public static Key get(int c) {
        return new Key(true, c);
    }
    public static Key get(char c, int code) {
        return c == PConstants.CODED ? new Key(true, code) : new Key(false, c);
    }
}
