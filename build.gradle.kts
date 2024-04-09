plugins {
    kotlin("jvm") version "1.9.23"
    id("net.weavemc.gradle") version "1.0.0-PRE"

}

group = "at.d23c.novax"
version = "1.0-SNAPSHOT"

minecraft {
    configure {
        name = "NovaX"
        modId = "novax"
        entryPoints = listOf("at.d23c.novax.NovaXMod")
        mixinConfigs = listOf("novax.mixins.json")
        mcpMappings()
    }
    version("1.8.9")

}

repositories {
    maven("https://jitpack.io")
    mavenCentral()
    maven("https://repo.spongepowered.org/maven")
    maven("https://repo.weavemc.dev/releases")
}

dependencies {
    implementation(files("./libs/LegacyEvents-1.0.jar"))
    implementation("net.weavemc.api:common:1.0.0-PRE")
    implementation("net.weavemc:internals:1.0.0-PRE")
    compileOnly("org.spongepowered:mixin:0.8.5")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}
