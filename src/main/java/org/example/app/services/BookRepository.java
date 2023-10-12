package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository<T> implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        logger.info("Start to adding book");
        if (!book.isAuthorEmpty() || !book.isSizeEmpty() || !book.isTitleEmpty()){
            book.setId(context.getBean(IdProvider.class).provideId(book));
            logger.info("store new book: " + book);
            repo.add(book);
        }
        else{
            logger.info("the store will not be done: some parameter is empty");
        }
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        for(Book book : retreiveAll()){
            if(book.getId().equals(bookIdToRemove)){
                logger.info("remove book completed: " + book);
                repo.remove(book);
                return true;
            }
        }
        logger.info("remove book not completed, incorrect Id is: "+ bookIdToRemove);
        return false;
    }

    @Override
    public boolean removeItemByRegex(String itemRegexToRemove) {
        int booksBeforeDelete = repo.size();
        for(Book book : retreiveAll()){
            if(book.getAuthor().equals(itemRegexToRemove) || book.getTitle().equals(itemRegexToRemove) || book.getSize().toString().equals(itemRegexToRemove)){
                logger.info("remove book completed: " + book);
                repo.remove(book);
            }
        }
        if(repo.size() == booksBeforeDelete){
            logger.info("books remove is not completed, incorrect Regex is: "+ itemRegexToRemove);
            return false;
        }
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void defaultInit(){
        logger.info("default INIT in book repo bean");
    }

    private void defaultDestroy(){
        logger.info("default DESTROY in book repo bean");
    }
}
