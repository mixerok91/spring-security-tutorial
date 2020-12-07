package by.stepanov.springsecuritytutorial.controller;

import by.stepanov.springsecuritytutorial.model.News;
import by.stepanov.springsecuritytutorial.service.NewsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/list")
    public ModelAndView getNewsList(ModelAndView model, Authentication authentication, HttpSession httpSession){

        if (authentication != null){
            if (authentication.getAuthorities().size() > 1){
                httpSession.setAttribute("isAdmin", true);
            } else {
                httpSession.setAttribute("isAdmin", false);
            }
        }

        List<News> newsList = newsService.getNewsList();
        model.addObject("newsList", newsList);
        model.setViewName("/news-list");
        return model;
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd(ModelAndView model){

        News news = new News();
        news.setDate(LocalDateTime.now());
        model.addObject("news", news);
        model.setViewName("/news-form");
        return model;
    }

    @PostMapping("/saveNews")
    public String saveNews(@ModelAttribute("news") News news) {

        newsService.saveNews(news);

        return "redirect:/news/list";
    }

    @GetMapping("/showFormForUpdate")
    public ModelAndView showFormForUpdate(@RequestParam("newsId") int newsId, ModelAndView model) {

        News news = newsService.getNews(newsId);
        model.addObject("news", news);
        model.setViewName("/news-form");
        return model;
    }

    @GetMapping("/deleteOneNews")
    public String deleteNews(@RequestParam("newsId") int newsId) {

        newsService.deleteNews(newsId);

        return "redirect:/news/list";
    }

    @GetMapping("/showOneNews")
    public ModelAndView showOneNews(@RequestParam("newsId") int newsId, ModelAndView model) {

        News news = newsService.getNews(newsId);
        model.addObject("news", news);
        model.setViewName("/show-news");
        return model;
    }

    @GetMapping("/deleteFewNews")
    public String deleteFewNews(HttpServletRequest request) {

        Enumeration<String> parameterNamesEnum = request.getParameterNames();
        List<String> parameterNames = Collections.list(parameterNamesEnum);
        if (!parameterNames.isEmpty()){
            for (String param:
                 parameterNames) {
                newsService.deleteNews(Long.parseLong(param));
            }
        }
        return "redirect:/news/list";
    }
}
