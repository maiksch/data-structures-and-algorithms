plugins {
	kotlin("jvm") version "1.8.22"
	application
}

repositories { mavenCentral() }

dependencies { implementation(kotlin("test")) }

application { mainClass.set("dsa.MainKt") }

sourceSets {
	test { kotlin { srcDirs("test") } }
	main { kotlin { srcDirs("src") } }
}
