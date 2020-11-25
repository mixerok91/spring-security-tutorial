package by.stepanov.springsecuritytutorial.service;

import by.stepanov.springsecuritytutorial.model.News;

import java.util.List;

public interface NewsService {
    List<News> getNewsList();

    News getNews(long newsId);

    void saveNews(News news);

    void deleteNews(long newsId);
}