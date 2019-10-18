import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class for the Library class.
 *
 * @author  Olav Valle
 * @version 2019/10/17
 */
@SuppressWarnings({"ConstantConditions", "WeakerAccess"})
public class LibraryTest {
    private Library library1;

    public LibraryTest() {
    }

    @BeforeEach
    public void setUp() {
        library1 = new Library();
    }

    /**
     * Asserts that the object created with "new Library();" is of the Library type.
     */
    @Test
    public void testLibraryConstructor()
    {
        assertTrue(library1 instanceof Library);
    }

    /**
     * Asserts that null cannot be added to library collection.
     */
    @Test
    public void testAddNullBook()
    {
        assertFalse(library1.addBook(null));
    }

    /**
     * Asserts that findAndRemove handles null parameter.
     */
    @Test
    public void testFindAndRemoveWithNull()
    {
        assertFalse(library1.findAndRemoveBook(null));
    }

    /**
     * Asserts that findAndRemove works, and only removes objects that are an exact match.
     */
    @Test
    public void testFindAndRemove()
    {
        library1.addBook(new Book("","","",
                "",100,"1234567891011")); //add book with known EAN13
        library1.addBook(new Book("","","",
                "",100,"1234567891010")); // almost identical EAN13

        assertEquals(2, library1.getLibrarySize()); // two books in library
        assertTrue(library1.findAndRemoveBook("1234567891011"));
        //assert that removing the above book succeeds

        assertEquals(1, library1.getLibrarySize()); // only one book remains
        assertEquals("1234567891010", library1.getLibraryIterator().next().getRefNumber());
        //assert that the book that remains is the one that should not have been removed
        //TODO assert that correct book was removed?
    }

    /**
     * Assert that a search with null as keyword is handled, and returns no matches.
     */
    @Test
    public void testSearchByNullKeyword()
    {
        //assert that search with a null keyword results in empty HashSet being returned
        assertEquals(0, library1.searchByKeyword(null).size());
    }

    /**
     * Assert that library size is correctly reported as library collection grows.
     */
    @Test
    public void testGetLibrarySize()
    {
        //adds books to library and asserts that library size increases accordingly
        for(int index = 0; index <= 10 ; index++){
            assertEquals(index, library1.getLibrarySize());
            library1.addBook(new Book(
                    "test" + index, "test" + index, "test" + index,
                    "test" + index, 100 + index, "test" + index));
                    //new Book
        }
    }

    /**
     * Assert that the iterator returned by library is initially empty,
     * and that this iterator is changed when books are added to collection.
     */
    @Test
    public void testGetLibraryIterator()
    {
        assertFalse(library1.getLibraryIterator().hasNext());
        //assert that iterator of empty library is also empty
        library1.addBook(new Book(
                "test", "test", "test",
                "test", 100, "test")); // add a book to library
        assertTrue(library1.getLibraryIterator().hasNext());
        // assert that iterator was updated to reflect change in library
    }

    /**
     * Unused test, can be used to assert that findAndRemove handles null in collection correctly
     */
    @Test
    public void testRemoveBookWithNullInLibrary()
    {
        Library lib = new Library();
        lib.addBook(null);
        lib.addBook(new Book("", "", "", "",
                100, "1234567891011"));
        assertTrue(lib.findAndRemoveBook("1234567891011"));
        // to test findAndRemoveBook when there is null in collection, alter this to assertFalse
        // and alter addBook method to accept null parameters
        // should then assert to false, since the first book it finds in lib is null
    }

    /**
     * Unused test, can be used to assert that searchByKeyword handles null in collection correctly
     */
    @Test
    public void testSearchByKeywordWithNullInLibrary()
    {
        HashSet<String> keyword = new HashSet<>();
        // searchByKeyword expects HashSet<String> as parameter

        keyword.add("test"); // fill keyword HashSet for test

        Library lib = new Library(); // new library instance

        lib.addBook(null); //add null element to library

        lib.addBook(new Book("test", "test", "test", "test",
                100, "test")); // add a book that should give a match for keyword

        assertEquals(1, lib.searchByKeyword(keyword).size());
        // change expected to 0 to and alter addBook method to accept null parameters
        // should then assert to false, since the first book it finds in lib is null
    }

}



