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

    // SpringBoot
    id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"


}


dependencies {
    // Use JUnit test framework.
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.+")
    implementation("mysql:mysql-connector-java:8.0.+")
    implementation("org.yaml:snakeyaml:1.33+")
    implementation("net.sourceforge.argparse4j:argparse4j:0.9.0+")

    // Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Selenium 
    implementation("org.seleniumhq.selenium:selenium-java:4.+")
    implementation("io.github.bonigarcia:webdrivermanager:5.+")
}

application {
    // Define the main class for the application.
    mainClass.set("app.App")
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

task("dbtest", JavaExec::class) {
    main = "app.SystemDatabaseTests"
    classpath = sourceSets["main"].runtimeClasspath
}
