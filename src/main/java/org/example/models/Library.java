package org.example.models;

import java.util.*;

public class Library {


    private List<Book> books = new ArrayList<>();

    public static List<Person> peopleOnQueue = new LinkedList<>();


    public Library(List<Book> books, List<Person> libraryUsersOnQueue) {
        this.books = books;
        this.peopleOnQueue = libraryUsersOnQueue;
    }


    public static List<Person> getPeopleOnQueue() {
        return peopleOnQueue;
    }

    public static void setPeopleOnQueue(List<Person> peopleOnQueue) {
        Library.peopleOnQueue = peopleOnQueue;
    }

}