import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class for the Library class.
 *
 * @author  Olav Valle
 * @version 2019/10/17
 */
public class LibraryTest
{
    private Library library1;

    public LibraryTest()
    {
    }

    @BeforeEach
    public void setUp()
    {
        library1 = new Library();
    }

    @Test
    public void testAddNullBook()
    {
        assertFalse(library1.addBook(null));
    }

    @Test
    public void testFindAndRemoveWithNull()
    {
        assertFalse(library1.findAndRemoveBook(null));
    }

    @Test
    public void testFindAndRemove()
    {
        library1.addBook(new Book("","","",
                "","","1234567891011")); //add book with known EAN13
        assertTrue(library1.findAndRemoveBook("1234567891011")); //assert that removing the above book succeeds
    }

    @Test
    public void testSearchByNullKeyword()
    {
        //assert that search with a null keyword results in empty HashSet being returned
        assertEquals(0, library1.searchByKeyword(null).size());
    }

    @Test
    public void testGetLibrarySize()
    {
        //adds books to library and asserts that library size increases accordingly
        for(int index = 0; index <= 10 ; index++){
            assertEquals(index, library1.getLibrarySize());
            library1.addBook(new Book(
                    "test" + index, "test" + index, "test" + index,
                    "test" + index, "test" + index, "test" + index));
                    //new Book
        }
    }
    @Test
    public void testGetLibraryIterator()
    {
        //TODO find a way to test this method. Some way to assert the type of the object it returns?
        assertFalse(library1.getLibraryIterator().hasNext()); //assert that iterator of empty library is also empty
        library1.addBook(new Book(
                "test", "test", "test",
                "test", "test", "test")); // add a book to library
        assertTrue(library1.getLibraryIterator().hasNext()); // assert that iterator now hasNext
    }
}



