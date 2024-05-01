// import org.springframework.boot.CommandLineRunner;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Component;

// @Component
// public class DbConnectionLogger implements CommandLineRunner {

//     private final JdbcTemplate jdbcTemplate;

//     public DbConnectionLogger(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         jdbcTemplate.execute((ConnectionCallback<Object>) conn -> {
//             System.out.println("Connected to database URL: " + conn.getMetaData().getURL());
//             return null;
//         });
//     }
// }