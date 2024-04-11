package at.d23c.novax.mixins

import net.minecraft.client.gui.GuiIngame
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@Mixin(GuiIngame::class)
class GuiIngameMixin {

    @Inject(method = ["renderGameOverlay"], at = [At(value = "HEAD")])
    fun rgoInject(partialTicks: Float, ci: CallbackInfo) {
        println("renderGameOverlay print")
    }

}