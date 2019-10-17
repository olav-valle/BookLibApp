import java.util.Iterator;
import java.util.List;

/**
 * A class that maintains information on a book.
 *
 * @author Olav Valle
 * @version 2019/10/17
 */
@SuppressWarnings("WeakerAccess")
public class Book
{
    private String title;
    private String authorName;
    private String publisher;
    private String date;
    private String pages;
    private String ean13;
    // using String for EAN-13 for now.
    // possible to change to Long, for arithmetic
    // TODO: find and implement an EAN-13 generator class?
    private boolean available;


    /**
     * Book object constructor.
     * User inputs relevant information.
     * Borrowed status initialises to false, since book has not been
     * available for loan until now.
     *
     * Class does not contain methods for changing String fields
     * after initialisation
     *
     * If null parameters are passed, the related fields are set to "PARAMETER_WAS_NULL"
     *
     * Warning: EAN-13 String does not demand 13 characters, and is only
     * placeholder for functional IAN/EAN-13 encoding.
     *
     *
     * @param bookTitle The title of the book.
     * @param bookAuthor The name of the author.
     * @param bookPublisher Name of the publisher.
     * @param publishingDate The year the bok was published.
     * @param bookPages Number of pages in book.
     * @param ean13 EAN-13 reference number.
     */

    public Book(String bookTitle, String bookAuthor, 
                String bookPublisher, String publishingDate, 
                String bookPages, String ean13)
    {

        if (bookTitle == null) {this.title = "PARAMETER_WAS_NULL";} else { this.title = bookTitle; }

        if (bookAuthor == null) {this.authorName = "PARAMETER_WAS_NULL";} else { this.authorName = bookAuthor; }

        if (bookPublisher == null) {this.publisher = "PARAMETER_WAS_NULL";} else { this.publisher = bookPublisher; }

        if (publishingDate == null) {this.date = "PARAMETER_WAS_NULL";} else { this.date = publishingDate; }

        if (bookPages == null) {this.pages = "PARAMETER_WAS_NULL";} else { this.pages = bookPages; }

        if (ean13 == null) {this.ean13 = "PARAMETER_WAS_NULL";} else { this.ean13 = ean13; }

        this.available = true;
    }

    // TODO: Add mutator for other fields,
    // e.g. in case original entry was misspelled?

// -------------------- mutator --------------------

    /**
     * Set borrowed status as boolean value.
     * Called when book is checked out from, or returned to library.
     * @param  status true to set book as "available for loan", false to set book as "not available for loan"
     */
    //TODO is it even possible to pass a null parameter?
    public void setAvailability(boolean status)
    {
        this.available = status;
    }

// -------------------- accessors --------------------

    /**
     * Returns book title.
     * @return book title
     */
    public String getTitle() { return this.title; }

    /**
     * Returns author name
     * @return author name
     */
    public String getAuthorName() { return this.authorName; }

    /**
     * Return book publisher name.
     * @return publisher name.
     */
    public String getPublisher() { return this.publisher; }

    /**
     * Returns publishing year of the book.
     * @return publishing year of the book.
     */
    public String getDate() { return this.date; }

    /**
     * Returns number of pages in book.
     * @return number of pages in book.
     */
    public String getPages() { return this.pages; }

    /**
     * Returns the EAN13 reference number of the book.
     * @return EAN-13 reference number.
     */
    public String getRefNumber() { return this.ean13; }

    /**
     * Returns false if book is available for loan, true if it is currently being borrowed.
     * @return false if available, true if on loan.
     */
    public boolean getAvailable() { return this.available; }

    /**
     * Compares keyword parameter with the book's details (title, author name etc.),
     * and returns a boolean true if a match is found, and false if no match is found.
      * @param keyword the keyword to compare with book details.
     * @return true if keyword matches book details, false if it does not or if keyword was null.
     */
    public boolean matchDetails(String keyword) {

        boolean match = false;
        Iterator<String> it = detailsIterator();
        while (keyword != null && it.hasNext() && !match) {
            if (it.next().toLowerCase().contains(keyword.toLowerCase())) {
                match = true;
            }// if
        }// while
        return match;
    }

    /**
     * Returns an iterator for a collection containing all the String fields of the book.
     * @return Iterator for collection of book detail strings.
     */
    private Iterator<String> detailsIterator()
    {
        return List.of(title, authorName, publisher, date, pages, ean13).iterator();
    }
}// class Book
