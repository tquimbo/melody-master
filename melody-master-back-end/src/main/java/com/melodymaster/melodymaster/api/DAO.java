// import org.hibernate.Session;
// import org.hibernate.SessionFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

// @Repository
// @Transactional
// public class YourDAOClass {

//     @Autowired
//     private SessionFactory sessionFactory;

//     public void clearSessionCache() {
//         Session session = sessionFactory.getCurrentSession();
//         session.clear();
//     }

//     // Other methods of your DAO class...
// }