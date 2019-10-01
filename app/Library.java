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
        library = new ArrayList<Book>();
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
        Book newBook = new Book(bookTitle, bookAuthor, bookPublisher, 
                                publishingDate, bookPages, ean13);
        addBook(newBook);
    }

    /*
     * Iterates over each book in library through invokeAllMethods,
     * and checks the return of each method against the keyword specified
     * by the user.
     *
     * Warning: see invokeAllMethods method below.
     *
     * @param kw User input keyword for search
     * @return Iterator of book objects that match search criteria
     */
    
    /*public Iterator<Book> filterByKeyword(String kw)
    {
        //Iterator<Book> it = getIterator();

        for(Book b : library) {
            invokeAllMethods(b).toString().toLowerCase(); 
        //TODO: how do I collect all the returns from invokeAllMethods??
            // as some collection, and then iterate with .contains?
            if (contains(kw);
        }
    }
    */
    
    /**
     * invokes each of the "get" methods belonging to the Object.
     * Obvious warning is that ANY method with "get" in name will be called,
     * even if it is not an accessor method.
     * @param obj Object whose methods are to be called
     * @return the object retuned by the methods that are invoked
     */
    public Object invokeAllMethods(Object obj) throws IllegalAccessException, InvocationTargetException
    {
        //Arrays.stream(methods).forEach(m -> System.out.println(m.invoke(obj)));
        Method[] met = obj.getClass().getDeclaredMethods();
        
        for(Method m : met)
        {
            if (m.getName().contains("get"))
            {
                try {
                    return (m.invoke(obj));
                    //System.out.println(m.invoke(obj));
                } catch (InvocationTargetException e) {
                    System.out.println("InvocationTargetException : method being invoked probably threw an exception? I don't know...");
                    throw e;
                } catch (IllegalAccessException e) {
                    System.out.println("IllegalAccessException : method being invoked is not accessible from here? I don't know...");
                    throw e;
                }
            }
        }
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

