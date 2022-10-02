plugins {
    `java-platform`
}

repositories {
    mavenCentral()
}

dependencies {
    constraints {
        api("org.springframework.cloud:spring-cloud-dependencies:2021.0.4")
        api("org.springframework.boot:spring-boot-starter-parent:2.7.4")

        api("net.devh:grpc-client-spring-boot-starter:2.13.1.RELEASE")
        api("net.devh:grpc-server-spring-boot-starter:2.13.1.RELEASE")
        api("net.devh:grpc-spring-boot-starter:2.13.1.RELEASE")

        api("io.grpc:grpc-netty-shaded:1.49.0")
        api("io.grpc:grpc-protobuf:1.49.0")
        api("io.grpc:grpc-stub:1.49.0")
        api("io.grpc:grpc-api:1.49.0")
        api("io.grpc:grpc-stub:1.49.0")
        api("io.grpc:grpc-testing:1.49.0")

        api("org.mapstruct:mapstruct:1.5.2.Final")
        api("org.mapstruct:mapstruct-processor:1.5.2.Final")
        api("javax.annotation:javax.annotation-api:1.3.2")
    }
}