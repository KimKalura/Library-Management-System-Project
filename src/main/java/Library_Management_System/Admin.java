package Library_Management_System;

public class Admin extends User {


    public Admin(String name) {
        super(name);
    }


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String toString() {
        return "Admin{}";
    }


    public void addBook(Book book, Library library) {
        Book foundBook = library.searchBookByCode(book.getISBNCode());
        if (!"".equals(foundBook.getISBNCode())) {
            book.setTotalNumberOfCopies(book.getTotalNumberOfCopies() + 1);
        } else {
            Book[] bookList = library.getBooklist();
            bookList[library.getNoOfAddedBooks()] = book;
            library.setNoOfAddedBooks(library.getNoOfAddedBooks() + 1);
        }

        //dupa titlu
        /* Book foundBook = library.searchBookByTitle(book.getTitle());
        if (!"".equals(foundBook.getTitle())) {
            //incrementez numarul total de copii
            book.setTotalNumberOfCopies(book.getTotalNumberOfCopies() + 1);
        } else {
            Book[] bookList = library.getBooklist();
            bookList[library.getNoOfAddedBooks()] = book;
            library.setNoOfAddedBooks(library.getNoOfAddedBooks() + 1);
        }*/
    }

    public boolean deleteBook(String ISBNCode, Library library) {
        Book[] bookList = library.getBooklist();
        int ISBNCodeIndex = getISBNCodeIndex(ISBNCode, library);
        if (ISBNCodeIndex == -1) {
            return false;
        }
        for (int i = ISBNCodeIndex; i < library.getBooklist().length - 1; i++) {
            bookList[i] = bookList[i + 1];
        }

        bookList[library.getNoOfAddedBooks() - 1] = null;
        library.setNoOfAddedBooks(library.getNoOfAddedBooks() - 1);
        return true;
    }

    private int getISBNCodeIndex(String ISBNCode, Library library) {
        for (int i = 0; i < library.getNoOfAddedBooks(); i++) {
            if (library.getBooklist()[i].getISBNCode().equals(ISBNCode)) {
                return i;
            }
        }
        return -1;
    }

    public void listAllBooks(Library library) {
        for (int i = 0; i < library.getBooklist().length; i++) {
            System.out.println(library.getBooklist()[i]);
        }
    }

    public void listBookDetails(String ISBNCode, Library library) {
        Book foundBook = library.searchBookByCode(ISBNCode);
        System.out.println(foundBook);
    }


    public void viewBorrowedBooks(String clientName, Library library) {
        for (int i = 0; i < library.getBooklist().length; i++) {
            if (clientName.equals(library.getBooklist()[i])) {
                System.out.println(library.getBooklist());
            }
        }
    }

}
