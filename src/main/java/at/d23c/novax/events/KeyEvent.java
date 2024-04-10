package at.d23c.novax.events;

import jdk.nashorn.internal.objects.annotations.Constructor;
import net.weavemc.api.event.Event;

public class KeyEvent extends Event {


    public int code;
    public KeyState state;

    public enum KeyState {
        DOWN,
        UP
    }

    public KeyEvent(int code, KeyState state) {
        this.code = code;
        this.state = state;
    }
}
