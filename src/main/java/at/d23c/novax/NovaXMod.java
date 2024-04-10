package at.d23c.novax;


import at.d23c.novax.events.KeyEvent;
import at.d23c.novax.mixins.GuiMixin;
import at.d23c.novax.modules.ModuleManager;
import net.weavemc.api.ModInitializer;
import net.weavemc.api.event.EventBus;
import net.weavemc.api.event.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

import java.lang.instrument.Instrumentation;
import java.security.Key;
import java.util.HashSet;
import java.util.Set;

public class NovaXMod implements ModInitializer {

     private ModuleManager moduleManager;




    @Override
    public void preInit(@NotNull Instrumentation instrumentation) {
        moduleManager = new ModuleManager();
        System.out.println("Initializing NovaX!");
        EventBus.subscribe(this);
        moduleManager.playerESP.enable();

    }



    @SubscribeEvent
    public void keyPress(KeyEvent e) {
        if(e.code == Keyboard.KEY_P){
            moduleManager.playerESP.toggle();
        }
    }


}