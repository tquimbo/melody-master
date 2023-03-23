// package com.melodymaster.melodymaster;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// @EntityScan("com.melodymaster.melodymaster.entity")
// public class MelodyMasterApplication {

//     public static void main(String[] args) {
//         SpringApplication.run(MelodyMasterApplication.class, args);
//     }

// }
package com.melodymaster.melodymaster;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.melodymaster.melodymaster.entity")
@EnableJpaRepositories(basePackages = "com.melodymaster.melodymaster.repository")
public class MelodyMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelodyMasterApplication.class, args);
    }

}