plugins {
	java
	idea
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.flywaydb.flyway") version "8.4.3"
}

group = "com.machinery"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	compileOnly("org.projectlombok:lombok:1.18.32")
	annotationProcessor("org.projectlombok:lombok:1.18.32")

	implementation("org.flywaydb:flyway-core")

	//Kakfa dependecy:
	implementation("org.springframework.kafka:spring-kafka")
}

tasks.withType<Test> {
	useJUnitPlatform()
}


flyway {
	url = "jdbc:postgresql://localhost:5432/limites"
	user = "limits"
	password = "JpaLimitsSpringData"
}


// build.dependsOn(flywayMigrate)
