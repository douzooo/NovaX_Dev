package at.d23c.novax.mixins;


import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Level;
import java.util.logging.Logger;

@Mixin({ GuiIngame.class })
public final class GuiMixin {


    @Inject(method = { "renderGameOverlay" }, at = { @At("HEAD") })
    public final void rgoInject(float partialTicks, CallbackInfo ci) {
        System.out.println((Object)"renderGameOverlay print");
    }

}
