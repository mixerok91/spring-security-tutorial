package by.stepanov.springsecuritytutorial.service;

import by.stepanov.springsecuritytutorial.model.User;

public interface UserService {

    void save(User user);

    User findByUserName(String username);
}
