package by.stepanov.springsecuritytutorial.dao;


import by.stepanov.springsecuritytutorial.model.Role;

public interface RoleDAO{
    Role getOne(Long roleId);
}