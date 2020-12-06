package by.stepanov.springsecuritytutorial.dao;

import by.stepanov.springsecuritytutorial.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO{

    private final SessionFactory sessionFactory;

    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getOne(Long roleId) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(RoleQuery.SELECT_ROLENAME_BY_ID);
        query.setParameter("roleId", roleId);
        Role role = new Role();
        role.setId(roleId);
        role.setName((String)query.getSingleResult());

        return role;
    }

    private static class RoleQuery{
        private static final String SELECT_ROLENAME_BY_ID = "select name from Role where id =: roleId";
    }
}
