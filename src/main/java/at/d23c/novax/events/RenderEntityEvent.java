package at.d23c.novax.events;

import net.minecraft.entity.EntityLivingBase;
import net.weavemc.api.event.CancellableEvent;
import net.weavemc.api.event.Event;

public class RenderEntityEvent extends Event {


    public EntityLivingBase entity;
    public double x,y,z;
    public float yaw,tick;

    public RenderEntityEvent(EntityLivingBase entity, double x, double y, double z, float yaw, float tick) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.tick = tick;
    }
}
