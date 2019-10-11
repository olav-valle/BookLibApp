import java.lang.reflect.Field;
import java.util.ArrayList;
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
    // using String for EAN-13 for now. 
    // possible to change to Long, for arithmetic 
    // TODO: find and implement an EAN-13 generator class?
    private String ean13;  
    private boolean borrowed;

    /**
     * User inputs relevant information. 
     * Borrowed status initialises to false, since book has not been 
     * available until now.
     * 
     * Class does not contain methods for changing String fields 
     * after initialisation
     * 
     * 
     * Warning: EAN-13 String does not demand 13 characters, and is only 
     * placeholder for functional IAN/EAN-13 encoding. 
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

    public String getTitle() { return this.title; }

    public String getName() { return this.authorName; }

    public String getPublisher() { return this.publisher; }

    public String getDate() { return this.date; }

    public String getPages() { return this.pages; }

    public String getRefNumber() { return this.ean13; }

    public boolean getBorrowed() { return this.borrowed; }
    
    // returns a human readable string with borrowed status
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
    public String detailString()
    {
        return "Book title: " + this.title + "\n"
                + "Author: " + this.authorName + "\n"
                + "Publisher: " + this.publisher + "\n"
                + "Date Published: " + this.date + "\n"
                + "Number of pages: " + this.pages + "\n"
                + getBorrowedAsString() + "\n"
                + "EAN-13 reference: " + this.ean13;

    }

    public boolean matchDetails(String keyword) {
        boolean match = false;
        Iterator<String> it = detailsIterator();
        while (it.hasNext() && !match) {
            if (it.next().contains(keyword)) {
                match = true;
            }
        }
        return match;
    }

    private Iterator<String> detailsIterator()
    {
        ArrayList<String> details = new ArrayList<>();
        details.add(title.toLowerCase());
        details.add(authorName.toLowerCase());
        details.add(publisher.toLowerCase());
        details.add(date.toLowerCase());
        details.add(pages.toLowerCase());
        details.add(ean13.toLowerCase());

        return details.iterator();

    }
}
