plugins {
    kotlin("jvm") version "2.0.0-Beta3"
}

group = "com.spbpu"
version = "1.0-SNAPSHOT"

val kotestVersion = "5.8.0"
val libslParserVersion = "stable-SNAPSHOT"
val javaStdlibApproximationsVersion = "experimental-SNAPSHOT"
val usvmVersion = "f9e3883e"
val jacodbVersion = "1.4.4"
val testFilesVersion = "2.0.0"
val kgitVersion = "1.0.6"
val ksmtVersion = "0.5.16"
val kfgVersion = "0.4.15"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven {
        url = uri("https://maven.pkg.github.com/UnitTestBot/usvm")
        credentials {
            username = project.findProperty("gpr.user").toString()
            password = project.findProperty("gpr.key").toString()
        }
    }
    maven("https://maven.apal-research.com")
}

dependencies {
    // LibSL
    implementation("com.github.vpa-research:libsl-parser:$libslParserVersion")

    // USVM
    implementation("org.usvm:usvm-core:$usvmVersion")
    implementation("org.usvm:usvm-jvm:$usvmVersion")
    implementation("org.usvm:usvm-jvm-api:$usvmVersion")
    implementation("org.usvm:usvm-jvm-instrumentation:$usvmVersion")
    implementation("org.usvm:usvm-jvm-instrumentation-collectors:$usvmVersion")
    implementation("org.usvm:usvm-util:$usvmVersion")

    // JacoDB
    implementation("org.jacodb:jacodb-api:$jacodbVersion")
    implementation("org.jacodb:jacodb-core:$jacodbVersion")
    implementation("org.jacodb:jacodb-analysis:$jacodbVersion")
    implementation("org.jacodb:jacodb-approximations:$jacodbVersion")

    // KSMT
    implementation("io.ksmt:ksmt-core:$ksmtVersion")
    implementation("io.ksmt:ksmt-z3:$ksmtVersion")

    implementation("org.vorpal.research:kfg:$kfgVersion")
}

dependencies {
    // Kotlin
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("de.joshuagleitze:kotest-files:$testFilesVersion")

    // Approximations
    testImplementation("com.github.UnitTestBot.java-stdlib-approximations:approximations:$javaStdlibApproximationsVersion")

    // Util
    testImplementation("com.github.sya-ri:kgit:$kgitVersion")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

kotlin {
    jvmToolchain(21)
}
