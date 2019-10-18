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
               "Date", 100, "EAN-13");
    }

    /**
     * Assert that the other fields in the object are correctly created, even if one parameter is null
     */
    @Test
    public void testNewBookWithOneNullParameter()
    {
        Book nullTitleBook = new Book(null, "Author", "Publisher",
                "Date", 100, "EAN-13");

        assertEquals("PARAMETER_WAS_NULL", nullTitleBook.getTitle()); // should have been set to default value
        assertEquals("Author", nullTitleBook.getAuthorName());
        assertEquals("Publisher", nullTitleBook.getPublisher());
        assertEquals("Date", nullTitleBook.getDate());
        assertEquals("100", nullTitleBook.getPages());
        assertEquals("EAN-13", nullTitleBook.getRefNumber());
    }

    /**
     * Asserts that all fields are correctly initialized to default value
     * if the constructor is passed only invalid (null and/or negative int) parameters.
     */
    @Test
    public void testNewBookWithNullParametersAndNegativePages()
    {
        Book nullBook = new Book(null, null, null,
                null, -100, null);

        assertEquals("PARAMETER_WAS_NULL", nullBook.getAuthorName());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getDate());
        assertEquals("PARAMETER_WAS_NEGATIVE_INT", nullBook.getPages());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getTitle());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getPublisher());
        assertEquals("PARAMETER_WAS_NULL", nullBook.getRefNumber());
    }

    /**
     * Asserts that all getters return the field values initialized in setUp
     */
    @Test
    public void testAllGetMethods() {
        assertEquals("Title", testBook.getTitle());
        assertEquals("Author", testBook.getAuthorName());
        assertEquals("Publisher", testBook.getPublisher());
        assertEquals("Date", testBook.getDate());
        assertEquals("100", testBook.getPages());
        assertEquals("EAN-13", testBook.getRefNumber());
        assertTrue(testBook.getAvailable()); //asserts that the "available" field was initialized to true
    }

    /**
     * Asserts that a book will not find false positives for a keyword its fields do not contain.
     */
    @Test
    public void testMatchDetailsFalse() {
        assertFalse(testBook.matchDetails("test"));
    }

    /**
     * Asserts that a book will find matches for keywords in all its String fields.
     */
    @Test
    public void testMatchDetailsTrue() {
        assertTrue(testBook.matchDetails("Title"));
        assertTrue(testBook.matchDetails("Author"));
        assertTrue(testBook.matchDetails("Publisher"));
        assertTrue(testBook.matchDetails("Date"));
        assertTrue(testBook.matchDetails("100"));
        assertTrue(testBook.matchDetails("EAN-13"));
    }

    /**
     * Asserts that a book will not find a false positive if keyword parameter is null.
     */
    @Test
    public void testMatchDetailsWithNullKeyword()
    {
        assertFalse(testBook.matchDetails(null));
    }

    /**
     * Asserts that setAvailability sets field to value of parameter
     */
    @Test
    public void testSetAvailabilityToFalse()
    {
        assertTrue(testBook.getAvailable()); //field is initialized to true in constructor

        testBook.setAvailability(false); //set to false
        assertFalse(testBook.getAvailable()); //assert that field has changed value

        testBook.setAvailability(true);//set back to true
        assertTrue(testBook.getAvailable());//assert that field has been changed back to true
    }

}

