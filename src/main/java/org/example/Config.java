package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public PersonService personService() {
        PersonService personService = new PersonService();
        personService.setName("Jack");
        return personService;
    }

    @Bean
    public BookService bookService() {
        BookService bookService = new BookService();
        bookService.setAuthor("Daniel's");
        return bookService;
    }
}
