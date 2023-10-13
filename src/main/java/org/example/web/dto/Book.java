package org.example.web.dto;

import javax.validation.constraints.*;

public class Book {
    private Integer id;
    @Size(min = 1, max = 15)
    @Pattern(regexp = "[A-Z]?[a-z]+")
    private String author;
    @Size(min = 1, max = 25)
    private String title;
    @Digits(integer = 4, fraction = 0)
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", size=" + size +
                '}';
    }

    public boolean isAuthorEmpty() {
        return author.isEmpty();
    }

    public boolean isTitleEmpty() {
        return title.isEmpty();
    }

    public boolean isSizeEmpty(){
        return size == null;
    }
}
