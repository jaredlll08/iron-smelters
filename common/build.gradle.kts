import com.blamejared.Versions

plugins {
    id("blamejared-java-conventions")
    id("net.neoforged.moddev")
}

neoForge {
    neoFormVersion = Versions.NEO_FORM
//    accessTransformers.add(project.file("src/main/resources/META-INF/accesstransformer.cfg").absolutePath)
    parchment {
        minecraftVersion = Versions.PARCHMENT_MINECRAFT
        mappingsVersion = Versions.PARCHMENT
    }
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
    compileOnly("io.github.llamalad7:mixinextras-common:0.3.5")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.3.5")
    compileOnly("net.darkhax.pricklemc:prickle-common-1.21.1:${Versions.PRICKLE}")
}

configurations {
    register("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    register("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    register("commonGenerated") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    add("commonJava", sourceSets.main.get().java.sourceDirectories.singleFile)
    add("commonResources", sourceSets.main.get().resources.sourceDirectories.singleFile)
    add("commonGenerated", file("src/generated/resources"))
}