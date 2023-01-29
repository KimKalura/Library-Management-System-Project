package Library_Management_System;

import java.util.Arrays;

public class Client extends User {
    private String[] borrowedBooksCodes;

    private int borrowedNumberOfBooks;


    public Client(String name, String[] borrowedBooksCodes, int borrowedNumberOfBooks) {
        super(name);
        this.borrowedBooksCodes = borrowedBooksCodes;
        this.borrowedNumberOfBooks = borrowedNumberOfBooks;
    }

    public String[] getBorrowedBooksCodes() {
        return borrowedBooksCodes;
    }

    public void setBorrowedBooksCodes(String[] borrowedBooksCodes) {
        this.borrowedBooksCodes = borrowedBooksCodes;
    }

    @Override
    public String toString() {
        return "Client{" +
                "borrowedBooksCodes=" + Arrays.toString(borrowedBooksCodes) +
                '}';
    }


    public boolean isBookAvailable(Library library, String ISBNCode) { //*
        for (int i = 0; i < library.getBooklist().length; i++) {
            if (library.getBooklist()[i].getISBNCode().equals(ISBNCode)) {
                if (library.getBooklist()[i].getBorrowedNumberOfCopies() < library.getBooklist()[i].getTotalNumberOfCopies()) {
                    return true;
                }
            }
        }
        return false;

    }

    public void viewAvailableBooks(Library library) {
        for (int i = 0; i < library.getBooklist().length; i++) {
            if (library.getBooklist()[i].getBorrowedNumberOfCopies() < library.getBooklist()[i].getTotalNumberOfCopies()) {
                System.out.println(library.getBooklist()[i]);
            }
        }
    }

    public void borrowBook(String ISBNCode, Library library) throws BookNotAvailableException, BookNotFoundException, BorrowedBooksException {
        Book foundBook = library.searchBookByCode(ISBNCode);
        if ("".equals(foundBook.getISBNCode())) {
            throw new BookNotFoundException("Book does not exist");
        }
        if (foundBook.getBorrowedNumberOfCopies() == foundBook.getTotalNumberOfCopies()) {
            throw new BookNotAvailableException("Book is not available");
        }

        if(borrowedNumberOfBooks>=5){
            throw new BorrowedBooksException("The limit of borrowed books is 5!");
        }
        this.borrowedBooksCodes[borrowedNumberOfBooks] = foundBook.getISBNCode();
        borrowedNumberOfBooks++;
        foundBook.setBorrowedNumberOfCopies(foundBook.getBorrowedNumberOfCopies() + 1);
        // or:
        //int newNumberOfCopies= foundBook.getBorrowedNumberOfCopies()+1;
        //foundBook.setBorrowedNumberOfCopies(newNumberOfCopies);
    }

    public void printBorrowedBooks() {
        for (int i = 0; i < borrowedBooksCodes.length; i++) {
            System.out.println(borrowedBooksCodes[i]);
        }
    }

    public boolean returnBook(String ISBNCode, Library library) {
        int ISBNCodeIndex = getISBNCodeIndex(ISBNCode);
        if (ISBNCodeIndex == -1) {
            return false;
        }
        for (int i = ISBNCodeIndex; i < borrowedBooksCodes.length - 1; i++) {
            borrowedBooksCodes[i] = borrowedBooksCodes[i + 1];
        }

        borrowedBooksCodes[borrowedNumberOfBooks - 1] = null;
        borrowedNumberOfBooks--;

        Book returnBook = library.searchBookByCode(ISBNCode);
        returnBook.setBorrowedNumberOfCopies(returnBook.getBorrowedNumberOfCopies()-1);
        return true;
    }

    private int getISBNCodeIndex(String ISBNCode) {
        for (int i = 0; i < borrowedBooksCodes.length; i++) {
            if (borrowedBooksCodes[i].equals(ISBNCode)) {
                return i;
            }
        }
        return -1;
    }

}