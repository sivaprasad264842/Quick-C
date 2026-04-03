package com.quickc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main entry point for the Quick-C e-commerce application.
 *
 * <p>
 * <strong>@SpringBootApplication</strong> is a convenience annotation that
 * combines:
 * <ul>
 * <li>@Configuration – marks this class as a source of bean definitions</li>
 * <li>@EnableAutoConfiguration – enables Spring Boot’s auto-configuration</li>
 * <li>@ComponentScan – scans for @Component, @Service, @Repository,
 *
 * @Controller, etc.</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>@ComponentScan</strong> is added explicitly to ensure all packages
 * under {@code com.quickc} (controllers, services, config, etc.) are
 * discovered.
 * </p>
 *
 * <p>
 * The {@code main} method launches the Spring Boot application. VS Code’s Java
 * extension will detect this class and offer a **Run | Debug** button above the
 * {@code main} method.
 * </p>
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.quickc")
public class QuickcApplication {

    /**
     * Application entry point.
     *
     * @param args command-line arguments (none required for normal operation)
     */
    public static void main(String[] args) {
        // Starts the Spring Boot application context
        SpringApplication.run(QuickcApplication.class, args);
    }
}
