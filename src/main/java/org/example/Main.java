package org.example;

import org.example.enums.Gender;
import org.example.enums.Role;
import org.example.models.Book;
import org.example.models.Person;
import org.example.services.implementation.LibraryServiceImpl;

public class Main {
    public static void main(String[] args) {
        //Creating instances of the person class
        Person juniorStudent = new Person("Maryjane Peter",15, "J25", Gender.FEMALE, Role.JUNIOR_STUDENT);
        Person seniorStudent = new Person("Dan Paker",15,"J25",Gender.MALE,Role.SENIOR_STUDENT);
        Person teacher = new Person("ThankGod Hawk",28,"T23",Gender.FEMALE,Role.TEACHER);


        Person librarian = new Person("Royal Mathias", 32,"L28",Gender.FEMALE,Role.LIBRARIAN);

        //Creating instances of the book class
        Book book = new Book("Joys Of Motherhood",2);
        Book book1 = new Book("Half of a Yellow Sun", 2);


        LibraryServiceImpl libraryService = new LibraryServiceImpl();


        // THIS IS TO ADD TO THE QUEUE
        libraryService.requestBook(juniorStudent,book);
        libraryService.requestBook(seniorStudent,book);
        libraryService.requestBook(teacher,book);


        // THIS IS WITHOUT PRIORITY
        libraryService.addToQueueOnFirstCome(juniorStudent);
        libraryService.addToQueueOnFirstCome(seniorStudent);
        libraryService.addToQueueOnFirstCome(teacher);




        System.out.println("This is the Request");
        System.out.println(libraryService.requestBook(juniorStudent,book));
        System.out.println(libraryService.requestBook(seniorStudent,book));
        System.out.println(libraryService.requestBook(teacher,book));

        System.out.println("**************************************************************");

//        System.out.println("THIS IS WITH PRIORITY");
//        System.out.println(libraryService.giveBookOnPriority(book, librarian));
//        System.out.println(libraryService.giveBookOnPriority(book, librarian));
//        System.out.println(libraryService.giveBookOnPriority(book, librarian));
//        System.out.println("**************************************************************");


        System.out.println("THIS IS WITHOUT PRIORITY");
        System.out.println(libraryService.giveBookOnFirstCome(book1,librarian));
        System.out.println(libraryService.giveBookOnFirstCome(book1,librarian));
        System.out.println(libraryService.giveBookOnFirstCome(book1,librarian));
        System.out.println("****************************************************************");


    }
}


