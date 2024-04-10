//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package at.d23c.novax.mixins;

import at.d23c.novax.events.RenderWorldEvent;
import net.minecraft.client.renderer.EntityRenderer;
import net.weavemc.api.event.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRenderer.class})
public class EntityRendererMixin {
    public EntityRendererMixin() {
    }


    @Inject(
        method = {"renderWorldPass"},
        at = {@At("HEAD")}
    )
    public void onRenderWorld(int a, float ticks, long c, CallbackInfo ci) {
        EventBus.postEvent(new RenderWorldEvent(ticks));
    }
}
