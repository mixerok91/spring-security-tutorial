package by.stepanov.springsecuritytutorial.controller;

import by.stepanov.springsecuritytutorial.model.News;
import by.stepanov.springsecuritytutorial.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/list")
    public String getNewsList(Model model, Authentication authentication, HttpSession httpSession){

        if (authentication != null){
            if (authentication.getAuthorities().size() > 1){
                httpSession.setAttribute("isAdmin", true);
            } else {
                httpSession.setAttribute("isAdmin", false);
            }
        }

        List<News> newsList = newsService.getNewsList();
        model.addAttribute("newsList", newsList);

        return "/news-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        News news = new News();
        news.setDate(LocalDateTime.now());
        model.addAttribute("news", news);

        return "/news-form";
    }

    @PostMapping("/saveNews")
    public String saveNews(@ModelAttribute("news") News news) {

        newsService.saveNews(news);

        return "redirect:/news/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("newsId") int newsId, Model model) {

        News news = newsService.getNews(newsId);
        model.addAttribute("news", news);

        return "/news-form";
    }

    @GetMapping("/deleteOneNews")
    public String deleteNews(@RequestParam("newsId") int newsId) {

        newsService.deleteNews(newsId);

        return "redirect:/news/list";
    }

    @GetMapping("/showOneNews")
    public String showOneNews(@RequestParam("newsId") int newsId, Model model) {

        News news = newsService.getNews(newsId);
        model.addAttribute("news", news);

        return "/show-news";
    }

    @GetMapping("/deleteFewNews")
    public String deleteFewNews(HttpServletRequest request) {

        Enumeration<String> parameterNamesEnum = request.getParameterNames();
        List<String> parameterNames = Collections.list(parameterNamesEnum);
        if (!parameterNames.isEmpty()){
            for (String param:
                 parameterNames) {
                newsService.deleteNews(Long.valueOf(param));
            }
        }
        return "redirect:/news/list";
    }
}
