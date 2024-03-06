repositories {
    google()
    mavenCentral()
}
plugins {
    `kotlin-dsl`
    id("maven-publish")
}

group = "com.peanut.r8checker"
version = "1.0.0"

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

//afterEvaluate {
//    publishing {
//        publications {
//            // Creates a Maven publication called "release".
//            release(MavenPublication) {
//                // Applies the component for the release build variant.
//                from components.release
//                        artifact packAndroidJar
//                        // You can then customize attributes of the publication as shown below.
//                        groupId = 'com.wepie.weplay'
//                artifactId = 'base-ui'
//                version = snapshotVersion
//            }
//            // Creates a Maven publication called “debug”.
//            debug(MavenPublication) {
//                // Applies the component for the debug build variant.
//                from components.debug
//                        artifact packAndroidJar
//                        groupId = 'com.wepie.weplay'
//                artifactId = 'base-ui'
//                version = snapshotVersion
//            }
//        }
//        repositories {
//            maven {
//                url uri('https://maven.17zjh.com/repository/weflutter/')
//                credentials {
//                    username "weflutter"
//                    password "weflutter"
//                }
//            }
//        }
//    }
//}