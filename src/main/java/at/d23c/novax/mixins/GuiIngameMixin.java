package at.d23c.novax.mixins;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class GuiIngameMixin extends Gui {
    @Inject(method = "renderGameOverlay", at = @At("HEAD"))
    public void renderGameOverlayInject(float partialTicks, CallbackInfo ci) {
        System.out.println("Render Game Overlay Mixin");
    }
}