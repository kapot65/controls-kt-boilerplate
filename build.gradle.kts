plugins {
    kotlin("multiplatform") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.kotlin.link")
}

val controlsVersion = "0.3.0"
val ktorVersion = "2.3.7"

kotlin {

    explicitApi = null
    sourceSets {
        commonMain {
            dependencies {
                // зависимости, необходимые для задания спеки девайса в common модуле
                implementation("space.kscience:controls-core:$controlsVersion")
            }
        }

        jsMain {
            dependencies {
                // зависимости, необходимые для работы с Magix на Js клиенте
                implementation("space.kscience:controls-magix:$controlsVersion")
                implementation("space.kscience:magix-rsocket:$controlsVersion")
            }
        }

        jvmMain {
            dependencies {
                implementation("space.kscience:controls-server:$controlsVersion") // startDeviceServer
                implementation("space.kscience:magix-server:$controlsVersion") // startMagixServer
                implementation("space.kscience:controls-magix:$controlsVersion") // launchMagixService
                implementation("space.kscience:magix-rsocket:$controlsVersion") // MagixEndpoint.rSocketWithWebSockets
                implementation("io.ktor:ktor-client-cio:$ktorVersion") // ktor client implementation
            }
        }
    }

    js {
        browser()
        binaries.executable()
    }

    jvm()
}