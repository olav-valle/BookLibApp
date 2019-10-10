import java.util.Iterator;
/**
* The user interface of the book library application.
* Presents the user with a CLI menu list of app functions.
* All search and print functionality is handled by this class,
* while input and the library is handled externally.
*@author Olav Valle
*@version 0.1-20190924
*/

//TODO Move search functions and list functions to separate class
    //TODO alternative: handle search and list in the Library class 

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
			//TODO replace with switch statement
			if(userInput == 4){
			printFarewell();
			exit = true;
			}
			else if(userInput == 1) {
			//searchByTitle();
			searchByAuthor();
			}
			else if(userInput == 3) {
			listAllBooksIterator();
			}
		}
    }


    /**
    * Takes in author name from user, passes it to Library object. Library filters its 
    * ArrayList using input, and returns an iterator object which is printed. 
    */
    private void searchByAuthor()
    {
	String userInput = reader.getStringInput().toLowerCase().trim();
	Iterator<Book> it = library.filterByAuthor(userInput);

	while(it.hasNext())
	{
	    printBookDetails(it.next());
	}
    }



    public void listAllBooksIterator()
    {
	Iterator<Book> it = library.getIterator();
	
	int index = 1;

	System.out.println("Listing all books in no particular order.");
	System.out.println("");
        System.out.println("###############################");
        System.out.println("");

	while(it.hasNext())
	{
	    printBookDetails(it.next());
	    System.out.println("");
	    System.out.println("###############################");
	    System.out.println("");
	    // retrieve next book object directly from iterator
	    // and call print with it
	    index++;
	}
    }

    private void printBookDetails(Book book)
    {
        System.out.println("Book title: " + book.getTitle());
        System.out.println("Author: " + book.getName());
        System.out.println("Publisher: " + book.getPublisher());
        System.out.println("Date published: " + book.getDate());
        System.out.println("Numer of pages: " + book.getPages());
        System.out.println(book.getBorrowedAsString());
        System.out.println("EAN-13 reference: " + book.getRefNumber());
    }

    private void printWelcome()
    {
	System.out.println("");
	System.out.println("Welcome to BookLibApp. What is your request?");
	System.out.println("1. Search the library");
	System.out.println("2. Add a book.");
	System.out.println("3. List all books.");
	System.out.println("4. Exit application.");
	System.out.println("Please type the number of the service you require.");
    }

    private void printFarewell()
    {
	System.out.println("Thank you. Goodbye.");
    }


    
}
