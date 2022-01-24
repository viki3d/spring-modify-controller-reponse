package com.viki3d.spring.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The main SpringBoot application class.
 *
 * @author viki3d
 */
@SpringBootApplication
public class Main {

  /** The SpringBoot application entry point. */
  public static void main(String[] args) {
    // Get the Spring Context
    ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
    // Ensures a graceful shutdown and calls the relevant destroy methods on 
    // your singleton beans so that all resources are released.
    context.registerShutdownHook();
  }

}