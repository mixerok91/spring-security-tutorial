package by.stepanov.springsecuritytutorial.dao;

import by.stepanov.springsecuritytutorial.model.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    private final SessionFactory sessionFactory;

    public NewsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<News> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<News> theQuery =
                currentSession.createQuery(NewsQuery.SELECT_ALL_NEWS, News.class);

        return theQuery.getResultList();
    }

    @Override
    public News getOne(long newsId) {

        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.get(News.class, newsId);
    }

    @Override
    public void save(News news) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(news);
    }

    @Override
    public void deleteById(long newsId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query query = currentSession.createQuery(NewsQuery.DELETE_NEWS_BY_ID);
        query.setParameter("newsId", newsId);

        query.executeUpdate();
    }

    private static class NewsQuery {
        private static final String SELECT_ALL_NEWS = "from News order by date DESC";
        private static final String DELETE_NEWS_BY_ID = "delete from News where id=:newsId";
    }
}