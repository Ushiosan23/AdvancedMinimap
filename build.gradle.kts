plugins {
	id("org.jetbrains.intellij") version "1.3.0"
	kotlin("jvm") version "1.6.10"
	java
}

/* ------------------------------------------------------------------
 * Global configuration
 * ------------------------------------------------------------------ */

group = "com.github.ushiosan23"
version = "0.0.1"

/* ------------------------------------------------------------------
 * Repositories
 * ------------------------------------------------------------------ */

repositories {
	mavenCentral()
}

/* ------------------------------------------------------------------
 * Dependencies
 * ------------------------------------------------------------------ */

dependencies {
	// Implementations
	implementation(kotlin("stdlib"))
	// Test libraries
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

/* ------------------------------------------------------------------
 * Intellij configurations
 * See: https://github.com/JetBrains/gradle-intellij-plugin/
 * ------------------------------------------------------------------ */

intellij {
	version.set("2021.3")
	downloadSources.set(true)
}

/* ------------------------------------------------------------------
 * Configure tasks
 * ------------------------------------------------------------------ */

tasks.patchPluginXml {
	// Load changelog file content
	val changeNotesFile = rootProject.file("changelog.html")
	if (changeNotesFile.exists()) {
		val changeNotesContent = changeNotesFile
			.readLines()
			.joinToString("\n")
		changeNotes.set(changeNotesContent.trimIndent())
	}
	// Load description file content
	val descriptionFile = rootProject.file("description.html")
	if (descriptionFile.exists()) {
		val descriptionContent = descriptionFile
			.readLines()
			.joinToString("\n")
		pluginDescription.set(descriptionContent.trimIndent())
	}
	// Configure plugin
	version.set(rootProject.version as String)
}

tasks.getByName<Test>("test") {
	useJUnitPlatform()
}
