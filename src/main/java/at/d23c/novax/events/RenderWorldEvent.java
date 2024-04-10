package at.d23c.novax.events;

import net.weavemc.api.event.Event;

import java.awt.*;

public class RenderWorldEvent extends Event
{
    public final float partialTicks;

    public RenderWorldEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
