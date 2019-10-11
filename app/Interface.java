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


    private void searchByKeyword()
	{
		String userInput = reader.getStringInput().toLowerCase().trim();
		for (String s : library.searchByKeyword(userInput)){
			printBookDetails(s);
		}
	}



    private void listAllBooksIterator()
    {
		Iterator<String> it = library.getDetailsIterator();

		System.out.println("Listing all books in no particular order.");
		System.out.println();
        System.out.println("###############################");
        System.out.println();

		while(it.hasNext())
		{
			printBookDetails(it.next());
			System.out.println();
			System.out.println("###############################");
			System.out.println();
			// retrieve next book object directly from iterator
			// and call print with it
		}
    }

    private void printBookDetails(String details)
    {
		System.out.println(details);
    }

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

    private void printFarewell()
    {
	System.out.println("Thank you. Goodbye.");
    }


    
}
