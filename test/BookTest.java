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
     * Assert that the other fields in the object are correctly created if title is null.
     */
    @Test
    public void testNewBookWithNullTitle()
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
     * Assert that the other fields in the object are correctly created if author is null.
     */
    @Test
    public void testNewBookWithNullAuthor()
    {

        Book nullAuthorBook = new Book("Title", null, "Publisher",
                "Date", 100, "EAN-13");

        assertEquals("Title", nullAuthorBook.getTitle());
        assertEquals("PARAMETER_WAS_NULL", nullAuthorBook.getAuthorName());// should have been set to default value
        assertEquals("Publisher", nullAuthorBook.getPublisher());
        assertEquals("Date", nullAuthorBook.getDate());
        assertEquals("100", nullAuthorBook.getPages());
        assertEquals("EAN-13", nullAuthorBook.getRefNumber());
    }
    /**
     * Assert that the other fields in the object are correctly created if publisher is null.
     */
    @Test
    public void testNewBookWithNullPublisher()
    {
        Book nullPublisherBook = new Book("Title", "Author", null,
                "Date", 100, "EAN-13");

        assertEquals("Title", nullPublisherBook.getTitle());
        assertEquals("Author", nullPublisherBook.getAuthorName());
        assertEquals("PARAMETER_WAS_NULL", nullPublisherBook.getPublisher());// should have been set to default value
        assertEquals("Date", nullPublisherBook.getDate());
        assertEquals("100", nullPublisherBook.getPages());
        assertEquals("EAN-13", nullPublisherBook.getRefNumber());
    }
    /**
     * Assert that the other fields in the object are correctly created if date is null.
     */
    @Test
    public void testNewBookWithNullDate()
    {
        Book nullDateBook = new Book("Title", "Author", "Publisher",
                null, 100, "EAN-13");

        assertEquals("Title", nullDateBook.getTitle());
        assertEquals("Author", nullDateBook.getAuthorName());
        assertEquals("Publisher", nullDateBook.getPublisher());
        assertEquals("PARAMETER_WAS_NULL", nullDateBook.getDate());// should have been set to default value
        assertEquals("100", nullDateBook.getPages());
        assertEquals("EAN-13", nullDateBook.getRefNumber());
    }
    /**
     * Assert that the other fields in the object are correctly created if page int is negative.
     */
    @Test
    public void testNewBookWithNegativePageInt()
    {
        Book negativePageIntBook = new Book("Title", "Author", "Publisher",
                "Date", -100, "EAN-13");

        assertEquals("Title", negativePageIntBook.getTitle());
        assertEquals("Author", negativePageIntBook.getAuthorName());
        assertEquals("Publisher", negativePageIntBook.getPublisher());
        assertEquals("Date", negativePageIntBook.getDate());
        assertEquals("PARAMETER_WAS_NEGATIVE_INT", negativePageIntBook.getPages());// should have been set to default value
        assertEquals("EAN-13", negativePageIntBook.getRefNumber());
    }
    /**
     * Assert that the other fields in the object are correctly created if EAN-13 is null.
     */
    @Test
    public void testNewBookWithNullEAN13()
    {
        Book nullEAN13Book = new Book("Title", "Author", "Publisher",
                "Date", 100, null);

        assertEquals("Title", nullEAN13Book.getTitle());
        assertEquals("Author", nullEAN13Book.getAuthorName());
        assertEquals("Publisher", nullEAN13Book.getPublisher());
        assertEquals("Date", nullEAN13Book.getDate());
        assertEquals("100", nullEAN13Book.getPages());
        assertEquals("PARAMETER_WAS_NULL", nullEAN13Book.getRefNumber());// should have been set to default value
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
     * Assert that the book allows being borrowed if it is available, but not if it is unavailable.
     */
    @Test
    public void testBorrowBook()
    {
        assertEquals(testBook.getAvailable(), testBook.borrowBook());
        //assert that book starts available for loan
        assertFalse(testBook.borrowBook());
        //assert that book is already being borrowed
        assertTrue(testBook.returnBook());
        //attempt to return book that is borrowed
        assertFalse(testBook.returnBook());
        //attempt to return book that is not borrowed
    }

}

