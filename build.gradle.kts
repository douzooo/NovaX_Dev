plugins {
    kotlin("jvm") version "1.9.23"
    id("net.weavemc.gradle") version "1.0.0-PRE2"

}

group = "at.d23c.novax"
version = "1.0-SNAPSHOT"

weave {
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
    maven("https://repo.weavemc.dev/releases")
    maven("https://repo.spongepowered.org/maven")
}

dependencies {
//    implementation(files("./libs/LegacyEvents-1.0.jar"))
    implementation("net.weavemc.api:common:1.0.0-PRE2")
    implementation("net.weavemc:internals:1.0.0-PRE2")
    compileOnly("org.spongepowered:mixin:0.8.5")
}

kotlin {
    jvmToolchain(8)
}

tasks.test {
    useJUnitPlatform()
}
