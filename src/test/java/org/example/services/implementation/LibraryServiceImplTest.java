package org.example.services.implementation;

import org.example.enums.Gender;
import org.example.models.Book;
import org.example.models.Library;
import org.example.models.Person;
import org.example.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


class LibraryServiceImplTest {

    private LibraryServiceImpl libraryService;
    private Person person1;
    private Person person2;
    private Person librarian;
    private Book bookAvailable;
    private Book bookNotAvailable;
    private List<Person> queue;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryServiceImpl();
        person1 = new Person("John Doe", 25, "ID123", Gender.MALE, Role.TEACHER);
        person2 = new Person("Jane Smith", 30, "ID124", Gender.FEMALE, Role.SENIOR_STUDENT);
        librarian = new Person("Libby Library", 45, "ID125", Gender.FEMALE, Role.LIBRARIAN);
        bookAvailable = new Book("Available Book",  5);
        bookNotAvailable = new Book("Unavailable Book",  0);
        queue = new LinkedList<>();
        Library.setPeopleOnQueue(queue);
    }

    @Test
    void testRequestBook_BookAvailable() {
        String result = libraryService.requestBook(person1, bookAvailable);
        assertEquals("John Doe has requested and received Available Book", result);
    }

    @Test
    void testRequestBook_BookNotAvailable() {
        String result = libraryService.requestBook(person1, bookNotAvailable);
        assertEquals("John Doe has requested for Unavailable Book but it is currently unavailable.", result);
    }

    @Test
    void testGiveBook_BookOnPriorityAvailable() {
        queue.add(person1);
        queue.add(person2);

        String result = libraryService.giveBookOnPriority(bookAvailable, librarian);
        assertEquals("John Doe has borrowed Available Book and it was issued by Libby Library", result);
        assertEquals(4, bookAvailable.getCurrentNoOfCopies());
    }

    @Test
    void testGiveBook_BookOnPriorityNotAvailable() {
        queue.add(person1);
        queue.add(person2);

        String result = libraryService.giveBookOnPriority(bookNotAvailable, librarian);
        assertEquals("Book Taken", result);
        assertEquals(0, bookNotAvailable.getCurrentNoOfCopies());
    }

    @Test
    void testAddToQueueOnFirstCome() {
        libraryService.addToQueueOnFirstCome(person1);
        libraryService.addToQueueOnFirstCome(person2);

        List<Person> peopleOnQueue = Library.getPeopleOnQueue();
        assertEquals(2, peopleOnQueue.size());
        assertEquals(person1, peopleOnQueue.get(0));
        assertEquals(person2, peopleOnQueue.get(1));
    }

    @Test
    void testGiveBookOnFirstCome_BookAvailable() {
        queue.add(person1);
        queue.add(person2);

        String result = libraryService.giveBookOnFirstCome(bookAvailable, librarian);
        assertEquals("John Doe has borrowed Available Book and it was issued by Libby Library", result);
        assertEquals(4, bookAvailable.getCurrentNoOfCopies());
    }

    @Test
    void testGiveBookOnFirstCome_BookNotAvailable() {
        queue.add(person1);
        queue.add(person2);

        String result = libraryService.giveBookOnFirstCome(bookNotAvailable, librarian);
        assertEquals("Book Taken", result);
        assertEquals(0, bookNotAvailable.getCurrentNoOfCopies());
    }
    @Test
    void testGiveBook_OnPriority_PriorityQueue() {
        // Add people to the queue
        libraryService.addToQueueOnFirstCome(person1);  // USER
        libraryService.addToQueueOnFirstCome(person2);  // LIBRARIAN

        // Borrow the book
        String result = libraryService.giveBookOnPriority(bookAvailable, librarian);

        // Assert the teacher gets the book first, not the students
        assertEquals("John Doe has borrowed Available Book and it was issued by Libby Library", result);
        assertEquals(4, bookAvailable.getCurrentNoOfCopies());

        // Borrow the book again to see if the senior student gets it next
        result = libraryService.giveBookOnPriority(bookAvailable, librarian);
        assertEquals("Jane Smith has borrowed Available Book and it was issued by Libby Library", result);
        assertEquals(3, bookAvailable.getCurrentNoOfCopies());
    }
}
