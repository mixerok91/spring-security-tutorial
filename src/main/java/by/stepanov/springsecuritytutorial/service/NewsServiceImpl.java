package by.stepanov.springsecuritytutorial.service;

import by.stepanov.springsecuritytutorial.dao.NewsDAO;
import by.stepanov.springsecuritytutorial.model.News;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    private final NewsDAO newsDAO;

    public NewsServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    @Transactional
    @Override
    public List<News> getNewsList() {
        return newsDAO.findAll();
    }

    @Transactional
    @Override
    public News getNews(long newsId) {
        return newsDAO.getOne(newsId);
    }

    @Transactional
    @Override
    public void saveNews(News news) {
        newsDAO.save(news);
    }

    @Transactional
    @Override
    public void deleteNews(long newsId) {
        newsDAO.deleteById(newsId);
    }
}