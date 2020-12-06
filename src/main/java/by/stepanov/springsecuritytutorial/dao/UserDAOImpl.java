package by.stepanov.springsecuritytutorial.dao;

import by.stepanov.springsecuritytutorial.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO{

    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery(UserQuery.SELECT_USER_BY_ID);
        query.setParameter("username", username);
        List<User> users = query.getResultList();

        if (!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(user);
    }

    private static class UserQuery{
        private static final String SELECT_USER_BY_ID = "from User where username =: username";
    }
}