package com.matawan.nicefc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * The main class for the Nicefc Application.
 *
 * <p>This class is annotated with {@link SpringBootApplication}, indicating that it is the main entry
 * point for the Spring Boot application. The {@link SpringBootApplication} annotation combines
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration EnableAutoConfiguration},
 * {@link org.springframework.context.annotation.ComponentScan ComponentScan}, and
 * {@link org.springframework.boot.autoconfigure.SpringBootApplication SpringBootApplication} itself.
 *
 * <p>The {@code main} method starts the Spring Boot application using {@link SpringApplication#run(Class, String...)}.
 *
 * @see SpringBootApplication
 */
@SpringBootApplication
public class NicefcApplication {
	/**
	 * The main method that starts the Nicefc Application.
	 *
	 * <p>This method uses {@link SpringApplication#run(Class, String...)} to start the Spring Boot application.
	 *
	 * @param args The command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(NicefcApplication.class, args);
	}

}
