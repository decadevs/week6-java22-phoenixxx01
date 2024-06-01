package org.example.models;
import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
    public class Book {
        private String bookTitle;
        private int currentNoOfCopies;

        //CONSTRUCTOR
        public Book(String bookTitle, int currentNoOfCopies) {
            this.bookTitle = bookTitle;
            this.currentNoOfCopies = currentNoOfCopies;
        }


        @Override
        public String toString() {
            return "Book{" +
                    "bookTitle='" + bookTitle + '\'' +
                    ", currentNoOfCopies=" + currentNoOfCopies +
                    '}';
        }
    }
