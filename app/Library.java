import com.sun.source.tree.LiteralTree;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        fillLibrary(); //adds books for testing
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

    /**
     * Returns iterator containing all objects in collection that match the given keyword.
     * @param keyword The keyword to search for.
     * @return Iterator containing all objects that match filter predicate.
     */
    public Iterator<String> search(HashSet<String> keyword)
    {
        ArrayList<String> matches = new ArrayList<>();
        keyword.forEach(kw -> library.stream() //for each String in the HashSet
                        .filter(book -> book.matchDetails(kw)) // ask object if it matches keyword
                        .forEach(book -> matches.add(book.detailString()))); // for each book that matches
        return matches.iterator();
    }

    /**
     * Returns the collection object this instance holds.
     * @return The library collection.
     */
    public ArrayList<Book> getCollection()
    {
	return library;
    }

    /**
     * Returns Iterator holding description strings for objects in collection.
     * @return Iterator holding String objects.
     */
    public Iterator<String> getDetailsIterator()
    {
        ArrayList<String> books = new ArrayList<>();
        library.forEach(book -> books.add(book.detailString()));
        return books.iterator();
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

