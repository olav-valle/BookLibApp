import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class BookTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BookTest
{
    private Book testBook;
    /**
     * Default constructor for test class BookTest
     */
    public BookTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        testBook = new Book("test", "test", "test", "test", "test", "test");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void newBookWithNullParameters()
    {
        Book nullBook = new Book(null, null, null, null, null, null);
        assertEquals("PARAMETER_WAS_NULL", nullBook.getAuthorName());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getDate());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getPages());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getTitle());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getPublisher());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getRefNumber());
    }

    @Test
    void testAllGetMethods() {
        assertEquals("test", testBook.getTitle());
        assertEquals("test", testBook.getAuthorName());
        assertEquals("test", testBook.getPublisher());
        assertEquals("test", testBook.getDate());
        assertEquals("test", testBook.getPages());
        assertEquals("test", testBook.getRefNumber());
        assertTrue(testBook.getAvailable());
    }

    @Test
    void matchDetails() {
        assertTrue(testBook.matchDetails("test"));
    }

    @Test
    public void matchDetailsWithNullKeyword()
    {
        assertFalse(testBook.matchDetails(null));
    }

    @Test
    public void setAvailabilityToFalse()
    {
        testBook.setAvailability(false);
        assertFalse(testBook.getAvailable());
    }

}

