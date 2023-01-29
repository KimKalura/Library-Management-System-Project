package Library_Management_System;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Book[] bookslist = new Book[100];
        Library library = new Library(bookslist, 0);
        String[] borrowedBooksCodes = new String[0];
        Client client = new Client("x", borrowedBooksCodes, 0);

        Admin admin = new Admin("y");
        Book book = new Book();

        Scanner console = new Scanner(System.in);
        int option;
        System.out.println("ADMIN or CLIENT:");
        int userOption = Integer.parseInt(console.nextLine());
        if (userOption == 1) {
            do {
                printMenuAdmin();
                option = Integer.parseInt(console.nextLine());
                performSelectedActionForAdmin(option, library, admin, book);
            }
            while (option != 7);
        } else {
            do {
                printMenuClient();
                option = Integer.parseInt(console.nextLine());
                performSelectedActionForClient(option, console, library, client);
            }
            while (option != 6);
        }
    }


    public static void getBookByTitle(Library library) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the Title of the Book you are looking for:");
        System.out.print("Title: ");
        String title = console.nextLine();
        Book foundBook = library.searchBookByTitle(title);
        if (foundBook == null) {
            System.out.println("The Title: " + title + " does not exist");
        } else {
            System.out.println(foundBook);
        }
    }

    public static void addBook(Admin admin, Library library, Book book) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the following details to add the books to the library:");

        System.out.println("ISBN code:");
        String ISBNCode = console.nextLine();
        System.out.println("Title:");
        String title = console.nextLine();
        System.out.println("Author:");
        String author = console.nextLine();
        System.out.println("Number of copies");
        int totalNumberOfCopies = console.nextInt();
        System.out.println("Number of borrowed copies");
        int borrowedNumberOfCopies = console.nextInt();

        admin.addBook(new Book(title, author, ISBNCode, totalNumberOfCopies, borrowedNumberOfCopies), library);
        System.out.println("The book has been successfully entered!" + book.getTitle());  //arata null la sf
    }

    public static void getBookByISBNCode(Library library) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the ISBNCode of the book you are looking for:");
        System.out.print("ISBN code: ");
        String code = console.nextLine();
        Book foundBook = library.searchBookByCode(code);
        if (foundBook == null) {
            System.out.println("The Code: " + code + " does not exist");
        } else {
            System.out.println(foundBook);
        }
    }

    public static void deleteBook(Admin admin, Library library) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the ISBN code of the book you want to delete:");
        System.out.print("ISBNCode: ");
        String codeToBeDeleted = console.nextLine();
        if (!admin.deleteBook(codeToBeDeleted, library)) {
            System.out.println("The  ISBNCode" + codeToBeDeleted + " does not exist!");
        } else {
            System.out.println("The book has been successfully deleted!");
        }
    }

    public static void performSelectedActionForAdmin(int option, Library library, Admin admin, Book book) {
        switch (option) {
            case 1:
                addBook(admin, library, book);
                break;
            case 2:
                admin.listAllBooks(library);;// afiseaza dar da null
                break;
            case 3:
                getBookByTitle(library);
                break;
            case 4:
                getBookByISBNCode(library);
                break;
            case 5:
                deleteBook(admin, library);
                break;
            case 6:
                //String clientName = console.next();
               // admin.viewBorrowedBooks(clientName, library);
                break;
            case 7:
                System.out.println("Session ended.");
                break;
            default:
                System.out.println("The option does not exist1");
        }
    }

    public static void performSelectedActionForClient(int option, Scanner console, Library library, Client client) {
        switch (option) {
            case 1:
                client.viewAvailableBooks(library);
                break;
            case 2:
                System.out.println("Insert books' ISBNCode you want to borrow:"); //*
                String ISBNCode = console.nextLine();
                try {
                    client.borrowBook(ISBNCode, library);
                } catch (BookNotAvailableException | BookNotFoundException | BorrowedBooksException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                System.out.println("Insert the title:");
                String title = console.nextLine();
                library.searchBookByTitle(title);
                break;
            case 4:
                System.out.println("Enter the ISBN code of the book you want to check if it is available in the library:");
                ISBNCode = console.next();
                client.isBookAvailable(library, ISBNCode);
                break;
            case 5:
                System.out.println("Enter the ISBN code to return the book:");
                ISBNCode = console.next();
                client.returnBook(ISBNCode, library);
                break;
            case 6:
                System.out.println("Session ended.");
                break;
            default:
                System.out.println("The option does not exist!");

        }
    }


    public static void printMenuAdmin() {
        System.out.println("MENIU:");
        System.out.println("- ADMIN -");
        System.out.println("1.Add a Book");
        System.out.println("2.List all books from library");
        System.out.println("3.Find Book by Title");
        System.out.println("4.Find Book by ISBNCode");
        System.out.println("5.Delete a Book");
        System.out.println("6.View all Borrowed Books ");
        System.out.println("7.EXIT");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Choose an option:");
    }

    public static void printMenuClient() {
        System.out.println("MENIU:");
        System.out.println("- CLIENT -");
        System.out.println("1.See all the books from the library");
        System.out.println("2.Borrow a Book");
        System.out.println("3.Find Book by Title");
        System.out.println("4.Check if a  book is available");
        System.out.println("5.Return a Book");
        System.out.println("6.EXIT");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Choose an option:");
    }
}
