plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.0.0"
    kotlin("jvm")
}

val names = "cakeparty"

repositories {
    mavenLocal()
    maven { url = uri("https://maven.aliyun.com/repository/public") }
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    maven { url = uri("https://oss.sonatype.org/content/groups/public/") }
    maven { url = uri("https://repo.dmulloy2.net/repository/public/") }
    maven { url = uri("https://repo.panda-lang.org/releases/") }
    maven { url = uri("https://jitpack.io/") }
    mavenCentral()
}

dependencies {
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("com.j256.ormlite:ormlite-jdbc:6.1")
    implementation("com.zaxxer:HikariCP:6.2.1")

    implementation("dev.rollczi:litecommands-bukkit:3.9.1")
    implementation("dev.rollczi:litecommands-core:3.9.1")
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    compileOnly("org.black_ixx:playerpoints:3.2.4")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
tasks.shadowJar {
    relocate("dev.rollczi.litecommands", "com.araykal.$names.shade.dev.rollczi.litecommands")
    relocate("net.kyori", "com.araykal.$names.shade.net.kyori")
    archiveClassifier = ""
}

tasks.build {
    dependsOn(tasks.shadowJar)
}