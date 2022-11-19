/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.5.1/userguide/building_java_projects.html
 */

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("io.qameta.allure") version "2.11.0"
    id("jacoco")
    id("io.ktor.plugin") version "2.1.2"
    id("org.sonarqube") version "3.4.0.2513"
    id("org.springframework.boot") version "2.5.0"
}



dependencies {
    // Use JUnit test framework.
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.+")

}

application {
    // Define the main class for the application.
    mainClass.set("auteline.ATMTest")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(false)
    }
}


ktor {
    fatJar {
        archiveFileName.set("autelineATM.jar")
    }
}

sonarqube {
  properties {
    property("sonar.projectKey", "ehharvey_auteline_AYRIQL5AHRjEj3eT5g-_")
  }
}
