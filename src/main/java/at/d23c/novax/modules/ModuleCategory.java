package at.d23c.novax.modules;

public enum ModuleCategory {

    RENDER("Render"),
    WORLD("World"),
    COMBAT("Combat"),
    EXPERIMENTAL("Experimental");


    public final String moduleName;

    ModuleCategory(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }
}
