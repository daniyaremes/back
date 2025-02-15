package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public PersonService personService() {
        PersonService personService = new PersonService();
        personService.setName("Daniyar");
        return personService;
    }

    @Bean
    public BookService bookService() {
        BookService bookService = new BookService();
        bookService.setTitle("Attack of Titan");
        bookService.setAuthor("Daniel's");
        return bookService;
    }
}
