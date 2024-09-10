
package com.melodymaster.melodymaster;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.melodymaster.melodymaster"})
@EntityScan("com.melodymaster.melodymaster.entity")
@EnableJpaRepositories("com.melodymaster.melodymaster.repository")
public class MelodyMasterApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MelodyMasterApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting MelodyMasterApplication...");
        SpringApplication.run(MelodyMasterApplication.class, args);
    }

}