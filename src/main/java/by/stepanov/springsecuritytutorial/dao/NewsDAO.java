package by.stepanov.springsecuritytutorial.dao;


import by.stepanov.springsecuritytutorial.model.News;

import java.util.List;

public interface NewsDAO{

    List<News> findAll();

    News getOne(long newsId);

    void save(News news);

    void deleteById(long newsId);
}