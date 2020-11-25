package by.stepanov.springsecuritytutorial.dao;

import by.stepanov.springsecuritytutorial.model.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<News> findAll() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query  ... sort by last name
        Query<News> theQuery =
                currentSession.createQuery("from News order by date DESC",
                        News.class);

        // execute query and get result list
        List<News> newsList = theQuery.getResultList();

        // return the results
        return newsList;
    }

    @Override
    public News getOne(long newsId) {
        Session currentSession = sessionFactory.getCurrentSession();

        News news = currentSession.get(News.class, newsId);

        return news;
    }

    @Override
    public void save(News news) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(news);
    }

    @Override
    public void deleteById(long newsId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = currentSession.createQuery("delete from News where id=:newsId");
        query.setParameter("newsId", newsId);

        query.executeUpdate();
    }
}
