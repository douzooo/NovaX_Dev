package at.d23c.novax.mixins;


import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiIngame.class,priority = 1002)
public class GuiIngameMixin2 {
    @Inject(method = "renderGameOverlay",at = @At("HEAD"))
    public void renderGameOverlay(float var1, CallbackInfo ci){
        System.out.println("Rendering GUI");
    }
}
