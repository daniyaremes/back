package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        PersonService personService = context.getBean(PersonService.class);
        BookService bookService = context.getBean(BookService.class);

        personService.setName("Daniyar");
        bookService.setTitle("Attack of Titan");

        System.out.println("Person name: " + personService.getName());
        System.out.println("Book title: " + bookService.getTitle());
        System.out.println("Book author: " + bookService.getAuthor());

        context.close();
    }
}
