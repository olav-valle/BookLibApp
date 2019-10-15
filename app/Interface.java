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

        library.addBook(title, author, publisher, date, pages, ean13);
        System.out.println("###############################");
        System.out.println("The book was successfully added to the library");
        System.out.println("###############################");
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
        System.out.println("###############################");

        HashSet<String> userInput = reader.getStringInputAsSet();
        Iterator<String> it = library.searchByKeyword(userInput);

        if(it.hasNext()){ // if iterator is empty, the search had no matches
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
    }


    /**
     * Prints the details about each book stored in the collection.
     */
    private void listAllBooksIterator()
    {
        System.out.println("###############################");
        System.out.println("Listing all books in collection.");
        System.out.println("###############################");
        printIterator(library.getDetailsIterator());

    }

    /**
     * Prints all String objects held in the provided Iterator
     * @param it Iterator<String> holding objects to print.
     */
    private void printIterator(Iterator<String> it)
    {
        it.forEachRemaining(s -> printBookDetails(s));

        System.out.println();
        System.out.println("###############################");
        System.out.println("          End of list.");
        System.out.println("###############################");
    }

    /**
     * Prints the String object provided as parameter, with some surrounding formatting.
     * Example output:
     *                 "Text contained in string."
     *                 <empty line>
     *                 ###############################
     *                 <empty line>
     * @param details String that is to be printed.
     */
    private void printBookDetails(String details)
    {
        System.out.println();
        System.out.println("###############################");
        System.out.println();
        System.out.println(details);
    }

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
        System.out.println("Please type the number of the service you require.");
        System.out.println("###############################");
    }

    /**
     * Prints a message asking the user to input a valid number for a menu item .
     */
    private void printInputError()
    {
        System.out.println("###############################");
        System.out.println("Error: Please input a valid number.");
        System.out.println("###############################");
    }

    /**
     * Prints a farewell message to the user.
     */
    private void printFarewell()
    {
        System.out.println("Thank you. Goodbye.");
    }
}
