plugins {
    java
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.sun.xml:jaxb-impl:2.0EA3")
    implementation("javax.activation:javax.activation-api:1.2.0")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    implementation("com.github.f4b6a3:uuid-creator:5.3.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //uuid-create



}

tasks.withType<Test> {
    useJUnitPlatform()
}
