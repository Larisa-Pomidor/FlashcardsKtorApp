val ktor_version: String by project
val kotlin_version: String by project
val koin_ktor_version: String by project
val koin_ksp_version: String by project

plugins {
    kotlin("multiplatform") version "1.7.10"
    kotlin("plugin.serialization") version "1.5.30"
    application
}

group = "me.larisa.pomidor"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                //implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:2.0.1")
                implementation("io.ktor:ktor-server-html-builder-jvm:2.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
                implementation("org.ktorm:ktorm-core:3.6.0")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
                implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.7")
                implementation("org.postgresql:postgresql:42.7.1")
                implementation("org.slf4j:slf4j-api:1.7.32")
                implementation("ch.qos.logback:logback-classic:1.2.6")

                // koin
                implementation("io.insert-koin:koin-ktor:3.4.1")
                implementation("io.insert-koin:koin-logger-slf4j:3.4.1")
                implementation("io.insert-koin:koin-annotations:1.2.1")
                implementation("io.insert-koin:koin-annotations:1.2.1")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.467")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.467")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.3.0-pre.467")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.10.5-pre.467")

                implementation("io.ktor:ktor-client-js:2.2.4")
                implementation("io.ktor:ktor-client-content-negotiation:2.2.4")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.4")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("me.larisa.pomidor.application.ServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}

tasks.withType<Jar> {
    doLast {
        println("Task Name: $name")
        println("Task Type: ${this@withType}")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

//    if (name.startsWith("jsJar")) {
//        archiveFileName.set("frontend.jar")
//        manifest {
//            attributes["Main-Class"] = "me.larisa.pomidor.application.ServerKt"
//        }
//        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//
//        from(sourceSets.main.get().output)
//
//        dependsOn(configurations.runtimeClasspath)
//        from({
//            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
//        })
//    }

    if (name.startsWith("jvmJar")) {
        archiveFileName.set("backend.jar")
        manifest {
            attributes["Main-Class"] = "me.larisa.pomidor.application.ServerKt"
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(sourceSets.main.get().output)

        dependsOn(configurations.runtimeClasspath)
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }
}