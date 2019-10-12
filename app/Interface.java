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
                case 4:
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
                    //todo make addBook interface method
                    System.out.println("That feature is not yet implemented.");
                    break;
            }

        }
    }


    /**
     * Searches the collections for matches to a string input by the user.
     */
    private void searchByKeyword()
    {
        //String userInput = reader.getStringInput().toLowerCase().trim();
        HashSet<String> userInput = reader.getStringInputAsSet();
        Iterator<String> it = library.search(userInput);
        if(it.hasNext()){ // check if iterator is empty
            printIterator(it);
        }
        else { System.out.println("No match found");}
    }


    /**
     * Prints the details about each book stored in the collection.
     */
    private void listAllBooksIterator()
    {
        System.out.println("Listing all books in no particular order.");
        System.out.println();
        System.out.println("###############################");
        System.out.println();
        printIterator(library.getDetailsIterator());

    }

    /**
     * Prints all String objects held in the provided Iterator
     * @param it Iterator<String> holding objects to print.
     */
    private void printIterator(Iterator<String> it)
    {
        while(it.hasNext())
        {
            printBookDetails(it.next());
        }
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
        System.out.println(details);
        System.out.println();
        System.out.println("###############################");
        System.out.println();
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
        System.out.println("4. Exit application.");
        System.out.println("Please type the number of the service you require.");
    }

    /**
     * Prints a farewell message to the user.
     */
    private void printFarewell()
    {
        System.out.println("Thank you. Goodbye.");
    }
}
