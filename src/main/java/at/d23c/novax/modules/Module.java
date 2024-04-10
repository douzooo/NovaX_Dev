package at.d23c.novax.modules;

import net.weavemc.api.event.EventBus;

public class Module
{

    private final String moduleName;
    private final ModuleCategory moduleCategory;
    protected boolean enabled;

    public Module(String moduleName, ModuleCategory moduleCategory) {
        this.moduleName = moduleName;
        this.moduleCategory = moduleCategory;
    }

    public void enable(){
        this.enabled = true;

        EventBus.subscribe(this);
        onEnable();
    }

    public void disable(){
        this.enabled = false;
        EventBus.unsubscribe(this);

        onDisable();
    }


    public void toggle(){
        if(enabled) {
            disable();
        } else{
            enable();
        }
    }


    public void onDisable(){
        System.out.println("Disabled new module");

    }

    public void onEnable(){
        System.out.println("Enabled new module");
    }




}
