repositories {
    google()
    mavenCentral()
}
plugins {
    `kotlin-dsl`
}

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