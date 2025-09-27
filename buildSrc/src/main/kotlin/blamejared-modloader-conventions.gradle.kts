import com.blamejared.Properties
import com.blamejared.Versions
import org.gradle.jvm.tasks.Jar

plugins {
    id("blamejared-java-conventions")
    id("com.blamejared.gradle-mod-utils")
    id("com.modrinth.minotaur")
    id("net.darkhax.curseforgegradle")
}

configurations {
    register("commonJava") {
        isCanBeResolved = true
    }
    register("commonResources") {
        isCanBeResolved = true
    }
    register("commonGenerated") {
        isCanBeResolved = true
    }
}

dependencies {
    compileOnly(project(":common")) {
        capabilities {
            requireCapability("$group:${Properties.MODID}")
        }
    }
    "commonJava"(project(path = ":common", configuration = "commonJava"))
    "commonResources"(project(path = ":common", configuration = "commonResources"))
    "commonGenerated"(project(path = ":common", configuration = "commonGenerated"))

}

tasks {
    named<JavaCompile>("compileJava").configure {
        dependsOn(configurations.getByName("commonJava"))
        source(configurations.getByName("commonJava"))
    }

    named<ProcessResources>("processResources").configure {
        dependsOn(configurations.getByName("commonResources"))
        dependsOn(configurations.getByName("commonGenerated"))
        from(configurations.getByName("commonResources"))
        from(configurations.getByName("commonGenerated"))

    }

    named<Javadoc>("javadoc").configure {
        dependsOn(configurations.getByName("commonJava"))
        source(configurations.getByName("commonJava"))
    }

    named<Jar>("sourcesJar") {
        dependsOn(configurations.getByName("commonJava"))
        dependsOn(configurations.getByName("commonResources"))
        dependsOn(configurations.getByName("commonGenerated"))
        from(configurations.getByName("commonJava"))
        from(configurations.getByName("commonResources"))
        dependsOn(configurations.getByName("commonGenerated"))
    }

}

versionTracker {
    mcVersion.set(Versions.MINECRAFT)
    homepage.set(Properties.CURSE_HOMEPAGE)
    author.set(Properties.AUTHOR)
    projectName.set(Properties.NAME)
}