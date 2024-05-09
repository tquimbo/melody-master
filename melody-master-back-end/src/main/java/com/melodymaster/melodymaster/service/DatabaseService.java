// package com.melodymaster.melodymaster.service;

// import java.sql.SQLException;

// import javax.annotation.PostConstruct;
// import javax.sql.DataSource;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.stereotype.Service;

// @Service
// public class DatabaseService {

//     private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

//     private final DataSource dataSource;

//     @Autowired
//     public DatabaseService(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }

//                 @Bean
//                 public DataSource dataSource() {
//                     // create or retrieve the datasource
//                     return null; // replace null with the actual DataSource object
//                 }

//                 @PostConstruct
//                 public void logDatabaseUrl() {
//                     try {
//                         String dbUrl = dataSource.getConnection().getMetaData().getURL();
//                         logger.info("Database URL: {}", dbUrl);
//                     } catch (SQLException e) {
//                         logger.error("Error accessing database URL", e);
//                     }
//                 }

//     // other methods
// }
package com.melodymaster.melodymaster.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    private final DataSource dataSource;

    @Autowired
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void logDatabaseUrl() {
        try {
            String dbUrl = this.dataSource.getConnection().getMetaData().getURL();
            logger.info("Database URL: {}", dbUrl);
        } catch (Exception e) {
            logger.error("Failed to log database URL", e);
        }
    }

    // other methods...
}