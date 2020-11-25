package by.stepanov.springsecuritytutorial.dao;

//import by.stepanov.springsecuritytutorial.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface UserDAO extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//}

import by.stepanov.springsecuritytutorial.model.User;

public interface UserDAO{

    User findByUsername(String username);

    void save(User user);
}
