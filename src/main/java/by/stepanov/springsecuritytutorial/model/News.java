package by.stepanov.springsecuritytutorial.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "date", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "dd.MM.yyyy, HH:mm")
    private LocalDateTime date;

    @Column(name = "brief")
    private String brief;

    @Column(name = "text")
    private String text;

    public News() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFormatDate() {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm"));
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date.format(DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm")) +
                ", brief='" + brief + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}