repositories {
    google()
    mavenCentral()
}
plugins {
    id("maven-publish")
    `kotlin-dsl`
}

gradlePlugin {
    // Define the plugin
    plugins {
        register("R8CheckPlugin") {
            id = "R8CheckPlugin"
            implementationClass = "com.peanut.asm.r8check.R8CheckPlugin"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "17"
    }
}

group = "com.peanut.asm.r8check"
version = "1.4.0"

dependencies {
    implementation("com.android.tools.build:gradle:8.2.0")
    implementation(kotlin("gradle-plugin", "1.7.0"))
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.12")
    implementation("org.ajoberstar:grgit:1.1.0")
    implementation("com.android.tools.build:bundletool:1.6.0")
    compileOnly("com.android.tools.build:aapt2-proto:0.4.0")
    compileOnly("commons-io:commons-io:2.10.0")
    compileOnly("commons-codec:commons-codec:1.15")
    compileOnly("org.ow2.asm:asm-commons:9.3")
    compileOnly("org.ow2.asm:asm-tree:9.3")
}

//发布到本地仓库
publishing {
//    publications {
//        create<MavenPublication>("mavenJava") {
//            from(components["java"])
//            groupId = "com.peanut"
//            artifactId = "r8checker"
//            version = "2.0.0"
//        }
//    }
    publications {
        create<MavenPublication>("publication") {
            version = "0.0.1"
            artifactId = "Plugin"
            groupId = "com.example.visitorPlugin"
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}