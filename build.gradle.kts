plugins {
    application
    kotlin("jvm") version "1.3.72"
}

version = "1.0.0"
group = "de.moritz_muecke"

application {
    mainClass.set("de.moritz_muecke.rockpaperscissors.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}
