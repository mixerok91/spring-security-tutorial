package by.stepanov.springsecuritytutorial.dao;

import by.stepanov.springsecuritytutorial.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Role getOne(Long roleId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("select name from Role where id=:roleId");
        query.setParameter("roleId", roleId);
        Role role = new Role();
        role.setId(roleId);
        role.setName((String)query.getSingleResult());

        return role;
    }
}
