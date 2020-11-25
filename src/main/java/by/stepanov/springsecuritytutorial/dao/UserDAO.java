package by.stepanov.springsecuritytutorial.dao;

import by.stepanov.springsecuritytutorial.model.User;

public interface UserDAO{

    User findByUsername(String username);

    void save(User user);
}
