import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A library class that creates and manages an ArrayList of Book objects.
 * 
 * Books can be added, and a list of books in library can be printed.
 * 
 * @author Olav Valle   
 * @version 2019/10/17
 */

//TODO reconsider storing object collection as HashMap instead of ArrayList
    
@SuppressWarnings("WeakerAccess")
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
     * Add an existing book object to library collection.
     * @param existingBook a Book type object
     * @return boolean true if book was successfully added to collection, false if it failed (or if parameter was null)
     */
    public boolean addBook(Book existingBook)
    {
        boolean wasBookAdded;
        if(existingBook != null){
            wasBookAdded = this.library.add(existingBook); // Collection.add() returns boolean
        }
        else{
            wasBookAdded = false;
        }
        return wasBookAdded;
    }

    /**
     * Locates a book in the collection using its EAN-13 reference number (exact match),
     * and then removes it from the library.
     * @param ean13 The EAN-13 (ISBN) number of the book, as String type.
     * @return True if book was removed successfully, false if book was not removed or if parameter ean13 was null.
     */
    //TODO what if library contains null object?
    public boolean findAndRemoveBook(String ean13)
    {
        if(ean13 != null){ return library.removeIf(b -> b.getRefNumber().equals(ean13));}
        else { return false; }
    }


    /**
     * Filters the objects in the library using the HashSet of keywords provided.
     * @param keyword HashSet containing the individual keywords as Strings
     * @return HashSet containing all objects that match the keyword.
     */
    public HashSet<Book> searchByKeyword(HashSet<String> keyword) {
        HashSet<Book> matchingBooks = new HashSet<>();

        if (keyword != null) { //skips the search stream, and returns the empty HashSet if keyword object was null
            keyword.forEach(word -> // for each word in the keyword set
                    library.stream()
                           .filter(b -> b.matchDetails(word)) // ask each book if it matches keyword
                           .forEach(matchingBooks::add)); // add books that match to the set
        }
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
    //TODO how do I test this?
    public Iterator<Book> getLibraryIterator()
    {
        return library.iterator();
    }

    /**
     * Fills the library with a small collection of books for testing purposes.
     */

    //TODO refactor to test class, or set to DEBUG_ONLY?

    @SuppressWarnings("SpellCheckingInspection")
    public void fillLibrary()
    {
        addBook(new Book("The Colour of Magic", "Terry Pratchett", "Corgi", "1985", "285", "9780552124751"));
        addBook(new Book("The Light Fantastic", "Terry Pratchett", "Corgi", "1986", "241", "9780061020704"));
        addBook(new Book("A first course in machine learning (Second edition)", "Simon Rogers, Mark Girolami", "CRC Press", "2017", "397","9781498738484"));
        addBook(new Book("The Shadow of the Torturer", "Gene Wolfe", "Tom Doherty Associates, Inc.", "1982", "262", "9780671540661"));
        addBook(new Book("Molecular Gastronomy: Exploring the science of Flavor", "Hervé This", "Columbia University Press", "2006", "377", "9780231133128"));
        addBook(new Book("Les Halles Cookbook", "Anthony Bourdain", "Bloomsbury", "2004", "304", "9780747580126"));
        addBook(new Book("Larousse Gastronomique", "Prosper Montagné", "Éditions Larousse", "1938", "1087", "9780600620426"));
    }

}

