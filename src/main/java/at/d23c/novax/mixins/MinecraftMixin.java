package at.d23c.novax.mixins;

import at.d23c.novax.events.ClientTickEvent;
import at.d23c.novax.events.KeyEvent;
import net.minecraft.client.Minecraft;
import net.weavemc.api.event.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.lwjgl.input.Keyboard;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "startGame", at = @At("HEAD"))
    public void onStartGame(CallbackInfo ci) {
        System.out.println("Starting NovaX and injecting!");
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        EventBus.postEvent(new ClientTickEvent());
    }

    @Inject(
            method = {"dispatchKeypresses"},
            at = {@At("HEAD")},
            cancellable = true
    )
    public void onKey(CallbackInfo ci) {
        KeyEvent keyEvent = new KeyEvent(Keyboard.getEventKey(), (Keyboard.isKeyDown(Keyboard.getEventKey()) ? KeyEvent.KeyState.DOWN : KeyEvent.KeyState.UP));
        EventBus.postEvent(keyEvent);

    }
}