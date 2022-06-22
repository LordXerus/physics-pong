package lordxerus.pong2022.input;

import lordxerus.pong2022.annotation.NotNullByDefault;
import processing.core.PConstants;

import java.util.HashMap;
import java.util.Map;

@NotNullByDefault
public class KeyboardInput {

    private final Map<Character, Boolean> keyMap = new HashMap<>(200); // 200 keys on keyboard?
    private final Map<Integer, Boolean> codedMap = new HashMap<>(16); // 16 coded keys max?

    public final class Raw {
        public void notifyPressed(char c, int code) {
            if(c == PConstants.CODED) {
                codedMap.put(code, true);
            } else {
                keyMap.put(c, true);
            }
        }
        public void notifyReleased(char c, int code) {
            if(c == PConstants.CODED) {
                codedMap.put(code, false);
            } else {
                keyMap.put(c, false);
            }
        }
    }
    public final Raw raw = new Raw();

    public boolean getRaw(char c) {
        return keyMap.getOrDefault(c, false);
    }
    public boolean getRaw(int c) {
        return codedMap.getOrDefault(c, false);
    }
    public boolean getRaw(Key c) {
        if (c.coded) return codedMap.getOrDefault(c.value, false);
        else return keyMap.getOrDefault((char) c.value, false);
    }

    private final Map<String, Key> virtualMap = new HashMap<>();
    public boolean getInput(String input) {
        return getRaw(virtualMap.get(input));
    }
    public void setInput(String input, Key k) {
        virtualMap.put(input, k);
    }
}
