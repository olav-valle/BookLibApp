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
    private final String title;
    private final String authorName;
    private final String publisher;
    private final String date;
    private final String pages;
    private final String ean13;
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
                int bookPages, String ean13)
    {

        if (bookTitle == null) {this.title = "PARAMETER_WAS_NULL";}
        else { this.title = bookTitle; }

        if (bookAuthor == null) {this.authorName = "PARAMETER_WAS_NULL";}
        else { this.authorName = bookAuthor; }

        if (bookPublisher == null) {this.publisher = "PARAMETER_WAS_NULL";}
        else { this.publisher = bookPublisher; }

        if (publishingDate == null) {this.date = "PARAMETER_WAS_NULL";}
        else { this.date = publishingDate; }

        if(bookPages < 0) {this.pages = "PARAMETER_WAS_NEGATIVE_INT";}
        // checks if page number is non-negative
        else { this.pages = Integer.toString(bookPages); }

        if (ean13 == null) {this.ean13 = "PARAMETER_WAS_NULL";}
        else { this.ean13 = ean13; }

        this.available = true;
    }

// -------------------- mutator --------------------
    // TODO: Add mutator for other fields,
    // e.g. in case original entry was misspelled?


    /**
     * Set borrowed status as boolean value.
     * Called when book is checked out from, or returned to library.
     * @param  status true to set book as "available for loan", false to set book as "not available for loan"
     */
    public void setAvailability(boolean status)
    {
        this.available = status;
        //TODO is it even possible to pass a null parameter?

    }

// -------------------- accessors --------------------
    /**
     * Returns false if book is available for loan, true if it is currently being borrowed.
     * @return false if available, true if on loan.
     */
    public boolean getAvailable() { return this.available; }

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
     * Compares keyword parameter with the book's details (title, author name etc.),
     * and returns a boolean true if a match is found, and false if no match is found.
      * @param keyword the keyword to compare with book details.
     * @return true if keyword matches book details, false if it does not or if keyword was null.
     */
    public boolean matchDetails(String keyword)
    {
        boolean isMatch = false; //assume book is not a match until we have checked
        Iterator<String> it = detailsIterator(); //iterator of field values

        while (keyword != null && it.hasNext() && !isMatch) {
        //while keyword is valid, and more fields are available, and no match has been found.
            if (it.next().toLowerCase().contains(keyword.toLowerCase())) {
            //does the field contain the keyword?
                isMatch = true;
            }// if
        }// while

        return isMatch;
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
