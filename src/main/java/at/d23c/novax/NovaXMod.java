package at.d23c.novax;


import at.d23c.novax.mixins.GuiMixin;
import net.weavemc.api.ModInitializer;
import net.weavemc.api.event.EventBus;
import org.jetbrains.annotations.NotNull;

import java.lang.instrument.Instrumentation;

public class NovaXMod implements ModInitializer {


    @Override
    public void preInit(@NotNull Instrumentation instrumentation) {
        System.out.println("Initializing NovaX!");
        EventBus.subscribe(new GuiMixin());
    }
}