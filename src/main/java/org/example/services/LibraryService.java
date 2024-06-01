package org.example.services;

import org.example.models.Book;
import org.example.models.Person;

public interface LibraryService {

    public String requestBook( Person person, Book book );

    public String giveBookOnPriority(Book book, Person librarian);

   void addToQueueOnFirstCome(Person person);

    String giveBookOnFirstCome(Book bookTitle, Person librarian);




}