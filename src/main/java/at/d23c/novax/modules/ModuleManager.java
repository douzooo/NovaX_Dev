package at.d23c.novax.modules;

import at.d23c.novax.modules.impl.world.PlayerESP;

import java.util.ArrayList;

public class ModuleManager
{

    public ArrayList<Module> modules;
    public PlayerESP playerESP;

    public ModuleManager() {
        this.modules = new ArrayList<>();
        modules.add(this.playerESP = new PlayerESP("Player ESP",ModuleCategory.RENDER));
    }

}
