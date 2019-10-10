import java.lang.Object;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    }
    
    /**
     * Add an existing book object to list
     */
    public void addBook(Book existingBook)
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

    


    /* The previous attempt. Can't deal with all the lambda exception handling bullshit...
     List<Book> matchingBook = library.stream()
     .filter(b -> ((Search.invokeAllMethods(b)).toString().toLowerCase()).contains(kw))
     .collect(Collectors.toList());
     return matchingBook.iterator();
    */


    /**
    * Method to filter a stream of the library ArrayList, and retun the filtered results as a list.
    */
    public Iterator<Book> filterByAuthor(String author)
    {
	String aut = author;
	List<Book> matchingAuthor = library.stream()
	    .filter(b -> aut.equals(b.getName().toLowerCase()))
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

}

