import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * A library class that creates and manages an ArrayList of Book objects.
 * 
 * Books can be added, and a list of books in library can be printed.
 * 
 * @author Olav Valle   
 * @version 20190908
 */
public class Library
{
    // only field is the ArrayList holding the books
    private ArrayList<Book> library;
    
    /**
     * Constructor for objects of class Library
     */
    public Library()
    {
        // initialise library as ArrayList object of type Book 
        library = new ArrayList<>();
        fillLibrary();
    }
    
    /**
     * Add an existing book object to list
     */
    private void addBook(Book existingBook)
    {
        this.library.add(existingBook);
    }
    
    /**
     * Creates new Book instance, using the parameters given, by calling 
     * Book constructor, and adds the new book to library using addBook method.
     */
    
    public void addBook(String bookTitle, String bookAuthor,
                        String bookPublisher,String publishingDate, 
                        String bookPages, String ean13)
    {
        addBook(new Book(bookTitle, bookAuthor, bookPublisher, publishingDate, bookPages, ean13));
    }

    

    //TODO see if this can be used for anything?

    /* The previous attempt. Can't deal with all the lambda exception handling bullshit...
     List<Book> matchingBook = library.stream()
     .filter(b -> ((Search.invokeAllMethods(b)).toString().toLowerCase()).contains(kw))
     .collect(Collectors.toList());
     return matchingBook.iterator();
    */


    /**
    * Method to filter a stream of the library ArrayList, and return the filtered results as a list.
    */
    public Iterator<Book> filterByAuthor(String author)
    {
        List<Book> matchingAuthor = library.stream()
	    .filter(b -> author.equals(b.getName().toLowerCase()))
	    .collect(Collectors.toList());
	    return matchingAuthor.iterator();
    }

    public ArrayList<Book> getCollection()
    {
	return library;
    }
     
    public Iterator<Book> getIterator()
    {
	return library.iterator();
    }

    /**
     * Fills the library with a small collection of books
     * for testing purposes.
     */
    private void fillLibrary()
    {
        addBook("The Colour of Magic", "Terry Pratchett", "Corgi", "1985", "285", "9780552124751");
        addBook("The Light Fantastic", "Terry Pratchett", "Corgi", "1986", "241", "9780061020704");
        addBook("A first course in machine learning (Second edition)", "Simon Rogers, Mark Girolami", "CRC Press", "2017", "397","9781498738484");
        addBook("The Shadow of the Torturer", "Gene Wolfe", "Tom Doherty Associates, Inc.", "1982", "262", "9780671540661");
        addBook("Molecular Gastronomy: Exploring the science of Flavor", "Hervé This", "Columbia University Press", "2006", "377", "9780231133128");
        addBook("Les Halles Cookbook", "Anthony Bourdain", "Bloomsbury", "2004", "304", "9780747580126");
        addBook("Larousse Gastronomique", "Prosper Montagné", "Éditions Larousse", "1938", "1087", "9780600620426");
    }

}

