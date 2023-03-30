
package com.melodymaster.melodymaster;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.melodymaster.melodymaster"})
@EntityScan(basePackages = {"com.melodymaster.melodymaster.entity"})
@EnableJpaRepositories(basePackages = {"com.melodymaster.melodymaster.repository"})
public class MelodyMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelodyMasterApplication.class, args);
    }

}