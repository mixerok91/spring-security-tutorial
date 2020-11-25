package by.stepanov.springsecuritytutorial.dao;

import by.stepanov.springsecuritytutorial.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from User where username =: username");
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
}
