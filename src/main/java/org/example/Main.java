package org.example;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        PersonService personService = context.getBean(PersonService.class);
        BookService bookService = context.getBean(BookService.class);

        personService.setName("Daniyar");
        bookService.setTitle("Attack of Titan");
        System.out.println("Person name: " + personService.getName());
        System.out.println("Updated Book title: " + bookService.getTitle());
        System.out.println("Book author: " + bookService.getAuthor());

        context.close();
    }
}
