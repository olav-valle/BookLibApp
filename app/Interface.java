import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
/**
 * The user interface of the book library application.
 * Presents the user with a CLI menu list of app functions.
 * All print functionality is handled by this class,
 * while input and the library is handled externally.
 *@author Olav Valle
 *@version 0.1-20190924
 */

public class Interface
{
    private InputReader reader;
    private Library library;

    /**
     * Constructor
     */
    public Interface()
    {
        reader = new InputReader();
        library = new Library();
        library.fillLibrary(); // Fills library with sample books for testing
        //TODO move fillLibrary to test object,
        // or implement file reader and store book details externally

    }

    /**
     * The user interface method.
     */
    public void init()
    {
        boolean exit = false;


        while(!exit)
        {
            printWelcome();

            int userInput = reader.getIntInput();

            //this represents the main user interface menu
            switch (userInput) {
                case 5:
                    printFarewell();
                    exit = true;
                    break;
                case 1:
                    searchByKeyword();
                    break;
                case 3:
                    listAllBooksIterator();
                    break;
                case 2:
                    addBook();
                    break;
                case 4:
                    removeBook();
                    break;
                case 0:
                    printInputError();
                    break;
            }

        }
    }

    /**
     * Adds a book to the library collection, with details specified through user input.
     */
    private void addBook(){
        System.out.println("###############################");
        System.out.println("Please fill in book details.");
        System.out.println("###############################");
        System.out.println("Book title: ");
        String title = reader.getStringInput();

        System.out.println("Author name: ");
        String author = reader.getStringInput();

        System.out.println("Publisher: ");
        String publisher = reader.getStringInput();

        System.out.println("Publishing date: ");
        String date = reader.getStringInput();

        System.out.println("Number of pages: ");
        String pages = reader.getStringInput();

        System.out.println("EAN-13 reference number: ");
        String ean13 = reader.getStringInput();
        // finished asking user for input

        //TODO add check to test the user input values are valid, before sending to book object?
        Book newBook = new Book(title, author, publisher, date, pages, ean13); //construct new Book

        if(library.addBook(newBook)){ //create book and add to library
            System.out.println("###############################");
            System.out.println("The book \"" + newBook.getTitle() + "\" was successfully added to the library");
            System.out.println("Please confirm below that all details were correctly filled.");
            printBookDetails(newBook);
        }
        else{ System.out.println("Failed to add book. Please confirm that all fields are correctly filled.");}

    }

    /**
     * Removes a book from the collection, using the EAN-13 reference number to identify the correct book.
     */
    private void removeBook()
    {
        System.out.println("###############################");
        System.out.println("Please enter the EAN-13 reference number of the book you wish to delete.");
        System.out.println("Use the search function to find the correct reference.");
        System.out.println("Warning: This will remove ALL books that share this reference number");
        System.out.println("###############################");

        String userInput = reader.getStringInput();
        //TODO add a confirmation dialogue
        if(library.findAndRemoveBook(userInput)){
            System.out.println("###############################");
            System.out.println("Book successfully removed.");
            System.out.println("###############################");
        }
        else{
            System.out.println("###############################");
            System.out.println("There was an error while attempting to remove the book. \n " +
                                "Please double check the EAN-13 reference number.");
            System.out.println("###############################");
        }
    }

    /**
     * Searches the collections for matches to a string input by the user.
     */
    private void searchByKeyword()
    {
        System.out.println("###############################");
        System.out.print("Search: ");

        HashSet<String> userInput = reader.getStringInputAsSet();
        Iterator<Book> it = library.searchByKeyword(userInput).iterator();

        if(it.hasNext()){ // if iterator has objects, we assume the search had matches
            System.out.println("###############################");
            System.out.println("Match found. Showing details:");
            System.out.println("###############################");
            printIterator(it);
        }
        else {
            System.out.println("###############################");
            System.out.println("No match found");
            System.out.println("###############################");
        }
    } //searchByKeyword


    /**
     * Prints the details about each book stored in the collection.
     */
    //TODO Sort alphabetically before listing?
    private void listAllBooksIterator()
    {
        System.out.println("###############################");
        System.out.println("Listing all " + library.getLibrarySize() + " books in collection.");
        System.out.println("###############################");
        printIterator(library.getLibraryIterator());
    } //listAllBooksIterator

    /**
     * Prints all String objects held in the provided Iterator
     * @param it Iterator<Book> holding objects to print.
     */
    //TODO test for iterator and/or book in iterator being null
    private void printIterator(Iterator<Book> it)
    {
        it.forEachRemaining(b -> printBookDetails(b));

        System.out.println();
        System.out.println("###############################");
        System.out.println("          End of list.");
        System.out.println("###############################");
    } //printIterator

    /**
     * Prints the details of the Book object provided as parameter,
     * with some surrounding formatting.
     * Example output:
     *
     *  <empty line>
     *  ###############################
     *  <empty line>
     *  Book title:       Title
     *  Author:           Name
     *  Publisher:        Publisher
     *  Date Published:   20xx
     *  Number of pages:  100
     *  EAN-13 reference: 1234567891011
     *  This book is [currently on loan]/[available for loan]
     * @param book The book whose details shall be printed.
     */
    //TODO test for book being null
    private void printBookDetails(Book book) {
        if (book != null) {
            System.out.println();
            System.out.println("###############################");
            System.out.println();
            System.out.println("Book title:       " + book.getTitle());
            System.out.println("Author:           " + book.getAuthorName());
            System.out.println("Publisher:        " + book.getPublisher());
            System.out.println("Date Published:   " + book.getDate());
            System.out.println("Number of pages:  " + book.getPages());
            System.out.println("EAN-13 reference: " + book.getRefNumber());
            if (book.getBorrowed()) { System.out.println("This book is currently on loan."); }
            else { System.out.println("This book is available for loan."); }
        } // if null
    }// printBookDetails

    /**
     * Prints the menu that shows the available program functions to the user.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to BookLibApp. What is your request?");
        System.out.println("1. Search the library");
        System.out.println("2. Add a book.");
        System.out.println("3. List all books.");
        System.out.println("4. Remove a book.");
        System.out.println("5. Exit application.");
        System.out.println("Please enter the number of the service you require.");
        System.out.println("###############################");
    } //printWelcome

    /**
     * Prints a message asking the user to input a valid number for a menu item .
     */
    private void printInputError()
    {
        System.out.println("###############################");
        System.out.println("Error: Please input a valid number.");
        System.out.println("###############################");
    } //printInputError

    /**
     * Prints a farewell message to the user.
     */
    private void printFarewell()
    {
        System.out.println("Thank you. Goodbye.");
    } //printFarewell
}
