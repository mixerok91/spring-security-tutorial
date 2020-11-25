package by.stepanov.springsecuritytutorial.service;

import by.stepanov.springsecuritytutorial.dao.RoleDAO;
import by.stepanov.springsecuritytutorial.dao.UserDAO;
import by.stepanov.springsecuritytutorial.model.Role;
import by.stepanov.springsecuritytutorial.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getOne(1L));
        user.setRoles(roles);
        userDAO.save(user);
    }

    @Transactional
    @Override
    public User findByUserName(String username) {

        return userDAO.findByUsername(username);
    }
}
