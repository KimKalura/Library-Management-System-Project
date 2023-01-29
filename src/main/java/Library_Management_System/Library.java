package Library_Management_System;

import java.util.Arrays;

public class Library {
    private Book[] booklist;
    private int noOfAddedBooks;


    public Library(Book[] booklist, int noOfAddedBooks) {
        this.booklist = booklist;
        this.noOfAddedBooks = noOfAddedBooks;
    }

    public Book[] getBooklist() {
        return booklist;
    }

    public void setBooklist(Book[] booklist) {
        this.booklist = booklist;
    }

    public int getNoOfAddedBooks() {
        return noOfAddedBooks;
    }

    public void setNoOfAddedBooks(int noOfAddedBooks) {
        this.noOfAddedBooks = noOfAddedBooks;
    }


    public Book searchBookByCode(String ISBNCode) {
        Book foundBook = new Book("", "", "", 0, 0);
        for (int i = 0; i < noOfAddedBooks; i++) {
            if (ISBNCode.equals(booklist[i].getISBNCode())) {
                 foundBook = this.booklist[i];
            }
        }
        return foundBook;
    }

    public Book searchBookByTitle(String title) {
        for (int i = 0; i < booklist.length; i++) {
            if (booklist[i].getTitle().equals(title)) {
                return booklist[i];
            }
        }
        return new Book("", "", "", 0, 0);
    }


    @Override
    public String toString() {
        return "Library{" +
                "booklist=" + Arrays.toString(booklist) +
                ", noOfAddedBooks=" + noOfAddedBooks +
                '}';
    }
}
