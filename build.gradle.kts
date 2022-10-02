import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.6.21" apply false
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	id("com.google.protobuf") version "0.8.19" apply false
}

repositories {
	mavenCentral()
}

dependencies {
	platform(project(":shared:platform"))
}

buildscript {
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}
	dependencies {
		//Kotlin dependencies for gradle plugins
		//Different from project runtime
		classpath("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		classpath("org.jetbrains.kotlin:kotlin-reflect")
	}
	extra.apply {
		set("protobufVersion", "3.19.1")
		set("grpcVersion", "1.46.0")
	}
}

subprojects {
	group = "com.example"
	version = "1.0.0"

	apply {
		plugin("maven-publish")
	}

	repositories {
		mavenCentral()
	}

	tasks {

		withType<JavaCompile> {
			options.compilerArgs.add("-Xlint:all,-serial,-processing")
		}

		withType<KotlinCompile> {
			kotlinOptions.jvmTarget = "17"
			kotlinOptions.allWarningsAsErrors = true
			kotlinOptions.freeCompilerArgs += "-Xjvm-default=all"
		}

		withType<Jar> {
			archiveFileName.set("demo-${project.name}.jar")
		}

		withType<Test> {
			useJUnitPlatform()

			jvmArgs = listOf("-Xmx2048m")

			testLogging {
				events(org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED, org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED, org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED)
				showStandardStreams = true
				exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
			}
		}

		// show all subprojects dependencies `./gradlew allDeps`
		task("allDeps", DependencyReportTask::class) {}
	}
}
