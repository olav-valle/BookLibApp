import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class for the Book class.
 *
 * @author  Olav Valle
 * @version 2019/10/17
 */
public class BookTest
{
    private Book testBook;

    public BookTest()
    {
        //nothing to construct
    }

    @BeforeEach
    public void setUp()
    {
       testBook = new Book("Title", "Author", "Publisher",
               "Date", "Pages", "EAN-13");
    }

    /**
     * Assert that the other fields in the object are correctly created, even if one parameter is null
     */
    @Test
    public void newBookWithOneNullParameter()
    {
        Book nullTitleBook = new Book(null, "Author", "Publisher",
                "Date", "Pages", "EAN-13");

        assertEquals("PARAMETER_WAS_NULL", nullTitleBook.getTitle()); // should have been set to default value
        assertEquals("Author", nullTitleBook.getAuthorName());
        assertEquals("Publisher", nullTitleBook.getPublisher());
        assertEquals("Date", nullTitleBook.getDate());
        assertEquals("Pages", nullTitleBook.getPages());
        assertEquals("EAN-13", nullTitleBook.getRefNumber());
    }

    /**
     * Asserts that all fields are correctly initialized to default value if the constructor is passed only null parameters.
     */
    @Test
    public void newBookWithNullParameters()
    {
        Book nullBook = new Book(null, null, null,
                null, null, null);

        assertEquals("PARAMETER_WAS_NULL", nullBook.getAuthorName());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getDate());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getPages());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getTitle());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getPublisher());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getRefNumber());
    }

    /**
     * Asserts that all getters return the field values initialized in setUp
     */
    @Test
    void testAllGetMethods() {
        assertEquals("Title", testBook.getTitle());
        assertEquals("Author", testBook.getAuthorName());
        assertEquals("Publisher", testBook.getPublisher());
        assertEquals("Date", testBook.getDate());
        assertEquals("Pages", testBook.getPages());
        assertEquals("EAN-13", testBook.getRefNumber());
        assertTrue(testBook.getAvailable()); //asserts that the "available" field was initialized to true
    }

    /**
     * Asserts that a book will not find false positives for a keyword its fields do not contain.
     */
    @Test
    void matchDetailsFalse() {
        assertFalse(testBook.matchDetails("test"));
    }

    /**
     * Asserts that a book will find matches for keywords in all its String fields.
     */
    @Test
    void matchDetailsTrue() {
        assertTrue(testBook.matchDetails("Title"));
        assertTrue(testBook.matchDetails("Author"));
        assertTrue(testBook.matchDetails("Publisher"));
        assertTrue(testBook.matchDetails("Date"));
        assertTrue(testBook.matchDetails("Pages"));
        assertTrue(testBook.matchDetails("EAN-13"));
    }

    /**
     * Asserts that a book will not find a false positive if keyword parameter is null.
     */
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

