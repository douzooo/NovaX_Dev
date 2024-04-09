package at.d23c.novax.mixins;


import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Level;
import java.util.logging.Logger;

@Mixin(GuiIngame.class)
public class GuiMixin {


    @Inject(method = "renderGameOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", ordinal = 2))
    public void renderGameOverlay(float var1, CallbackInfo ci) {
System.out.println("Triggering Render Event");

    }


}
