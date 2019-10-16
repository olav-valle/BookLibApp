import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A library class that creates and manages an ArrayList of Book objects.
 * 
 * Books can be added, and a list of books in library can be printed.
 * 
 * @author Olav Valle   
 * @version 20190908
 */

//TODO reconsider storing object collection as HashMap instead of ArrayList
    
public class Library
{
    // only field is the ArrayList holding the books
    private ArrayList<Book> library;
    
    /**
     * Constructor
     */
    public Library()
    {
        // initialise library as ArrayList object of type Book 
        library = new ArrayList<>();
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
    //TODO refactor to Interface
    public void addBook(String bookTitle, String bookAuthor,
                        String bookPublisher,String publishingDate,
                        String bookPages, String ean13)
    {
        addBook(new Book(bookTitle, bookAuthor, bookPublisher, publishingDate, bookPages, ean13));
    }

    /**
     * Locates a book in the collection using its EAN-13 reference number (exact match),
     * and then removes it from the library.
     * @param ean13 The EAN-13 (ISBN) number of the book, as String type.
     * @return True if book was removed successfully, false if book was not removed.
     */
    public boolean findAndRemoveBook(String ean13)
    {
       return library.removeIf(b -> b.getRefNumber().equals(ean13));
    }

    /**
     * Remove a book from the collection.
     * @param book The book to be removed.
     * @return true if removal was successful, false if it failed.
     */
    private boolean remove(Book book)
    {
       return library.remove(book);
    }

    /**
     * Returns iterator containing all objects in collection that match the given keyword.
     * @param keyword The keywords to search for.
     * @return Iterator containing the details of  all objects that match filter predicate.
     */

    //TODO refactor to return book iterator instead of string.

    public Iterator<String> searchByKeyword(HashSet<String> keyword)
    {
        return (search(keyword)
                .stream() // passes keyword set to search method
                .map(Book::detailString) // calls on details of books in set of matching books
                .collect(Collectors.toCollection(HashSet::new))) // collects the detail strings in HashSet
                .iterator(); // returns iterator for the set of detail strings
    }

    /**
     * Filters the objects in the library using the HashSet of keywords provided.
     * @param keyword HashSet containing the individual keywords as Strings
     * @return HashSet containing all objects that match the keyword.
     */

    //TODO should this return a HashSet containing the matched books?
    // The set should by its very nature only contain each book once,
    // even though a single book may match several of the keywords.
    // Will this cause issues if it tries to add the same book again?

    //TODO make public and use to replace searchByKeyword

    private HashSet<Book> search(HashSet<String> keyword)
    {
        HashSet<Book> matchingBooks = new HashSet<>();

        keyword.forEach(word -> // for each word in the keyword set
                library.stream()
                       .filter(b -> b.matchDetails(word)) // ask each book if it matches keyword
                       .forEach(matchingBooks::add)); // add books that match to the set
        return matchingBooks; // return the set of book objects that match the keyword.

    }
    /**
     * Returns the collection object this instance holds.
     * @return The library collection.
     */
    public int getLibrarySize()
    {
	return library.size();
    }

    /**
     * Returns Iterator holding description strings for objects in collection.
     * @return Iterator holding descriptions of all books in collection.
     */
    public Iterator<Book> getLibraryIterator()
    {
        return library.iterator();
    }

    /**
     * Fills the library with a small collection of books for testing purposes.
     */

    //TODO refactor to test, or set to DEBUG_ONLY?

    public void fillLibrary()
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

