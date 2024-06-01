package org.example.services.implementation;
import lombok.Getter;
import lombok.Setter;
import org.example.models.*;
import org.example.services.LibraryService;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import static org.example.models.Library.peopleOnQueue;


@Getter
@Setter
public class LibraryServiceImpl implements LibraryService {

    /// To Request for a book
    @Override
    public String requestBook(Person person, Book book ) {
        peopleOnQueue.add(person);
        // Check if the book is available
        if (book.getCurrentNoOfCopies() == 0) {
            return person.getFullName() + " has requested for " + book.getBookTitle() + " but it is currently unavailable.";
        }
        // Process the queue using Stream API
        Optional<String> result = peopleOnQueue.stream()
                .filter(p -> book.getCurrentNoOfCopies() > 0)
                .findFirst()
                .map(p -> {
                    peopleOnQueue.remove(p);
                    return p.getFullName() + " has requested and received " + book.getBookTitle();
                });
        // Return the result or the fallback message
        return result.orElse(person.getFullName() + " has requested for " + book.getBookTitle() + " but it is currently unavailable.");

    }

    // TO Borrow A Book
    @Override
    public String giveBookOnPriority( Book bookTitle, Person librarian) {

        peopleOnQueue.sort(Comparator.comparing(Person::getRole));

        return this.giveBooks( bookTitle,librarian);


    }

    @Override
    public void addToQueueOnFirstCome(Person person) {
        peopleOnQueue.add(person);
    }

    @Override
    public String giveBookOnFirstCome(Book bookTitle, Person librarian) {


        return this.giveBooks(bookTitle,librarian);
    }


    private String giveBooks(Book bookTitle, Person librarian) {
        return Optional.ofNullable(!peopleOnQueue.isEmpty() ? peopleOnQueue.remove(0) : null)
                .map(person -> {
                    if (bookTitle.getCurrentNoOfCopies() > 0) {
                        bookTitle.setCurrentNoOfCopies(bookTitle.getCurrentNoOfCopies() - 1);
                        return person.getFullName() + " has borrowed " + bookTitle.getBookTitle() + " and it was issued by "
                                + librarian.getFullName();
                    } else {
                        peopleOnQueue.add(0, person); // Add person back to the front of the list
                        return "Book Taken";
                    }
                })
                .orElse("No one is on the Queue");


    }


}


