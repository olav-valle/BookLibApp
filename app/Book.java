import java.util.Iterator;
import java.util.List;

/**
 * A class that maintains information on a book.
 *
 * @author Olav Valle
 * @version 0.2-20190908
 */
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
    private boolean borrowed;


    /**
     * Book object constructor.
     * User inputs relevant information.
     * Borrowed status initialises to false, since book has not been
     * available for loan until now.
     *
     * Class does not contain methods for changing String fields
     * after initialisation
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
        this.title = bookTitle;
        this.authorName = bookAuthor;
        this.publisher = bookPublisher;
        this.date = publishingDate;
        this.pages = bookPages;        
        this.ean13 = ean13;
        this.borrowed = false;
    }

    // TODO: Add mutators for other fields, 
    // e.g. in case original entry was misspelled?

// -------------------- mutators --------------------

    /**
     * Set borrowed status as boolean value.
     * Called when book is checked out from, or returned to library.
     * @param  status   boolean of "the book is currently being borrowed."
     */
    public void setBorrow(boolean status)
    {
        this.borrowed = status;
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
    public boolean getBorrowed() { return this.borrowed; }

    /**
     * Returns a human readable string stating if the book is currently `available for loan or not.
     * @return string saying if book is available for loan or not.
     */
    //TODO should this be refactored?
    public String getBorrowedAsString()
    {
        String borrowedString;

        if (borrowed) {
            borrowedString = "This book is currently on loan.";
        }
        else {
            borrowedString = "This book is available for loan";
        }
        return borrowedString;
    }

    /**
     * Human readable print formatted string of book details.
     * @return Human readable book details.
     */
    //TODO Refactor!

    public String detailString()
    {
        return  "Book title:       " + this.title + "\n" +
                "Author:           " + this.authorName + "\n" +
                "Publisher:        " + this.publisher + "\n" +
                "Date Published:   " + this.date + "\n" +
                "Number of pages:  " + this.pages + "\n" +
                "EAN-13 reference: " + this.ean13 + "\n" +
                getBorrowedAsString();

    }

    /**
     * Compares keyword parameter with the book's details (title, author name etc.),
     * and returns a boolean true if a match is found, and false if no match is found.
      * @param keyword the keyword to compare with book details.
     * @return true if keyword matches book details, false if it does not.
     */
    public boolean matchDetails(String keyword) {
        boolean match = false;
        Iterator<String> it = detailsIterator();
        while (it.hasNext() && !match) {
            if (it.next().toLowerCase().contains(keyword)) {
                match = true;
            }
        }
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

}
