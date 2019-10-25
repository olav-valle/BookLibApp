/**
 * Holds information about an author.
 * @author Olav Valle
 * @version 0.1
 */

public class Author {

    /**
     * Builder class for an author instance.
     */
    public static class Builder
    {


        //builder class fields

        private String name;
        private int birthYear;

        private int deathYear;
        private int yearFirstPublished;

        /**
         * Author builder constructor. Also sets int 0 as placeholder value for int fields,
         * which can be overridden by calling other Builder methods.
         * @param name The name of the author.
         */
        public Builder(String name)
        {
            this.name = name;
            // sets default placeholder values for dates
            this.birthYear = 0;
            this.deathYear = 0;
            this.yearFirstPublished = 0;
        }
        /**
         * Sets the birth year of the author.
         * @param birthYear the birth year of the author.
         * @return the Builder object.
         */
        public Builder birthYear(int birthYear)
        {
            this.birthYear = birthYear;

            return this; //returns self so that it can be .notation daisy-chained with its other setters.
        }
        /**
         * Sets the year of death for the author.
         * @param deathYear year of death for the author.
         * @return the Builder object.
         */
        public Builder deathYear(int deathYear)
        {
            this.deathYear = deathYear;

            return this;
        }
        /**
         * Sets the year of the authors first published work.
         * @param yearFirstPublished year of the authors first published work.
         * @return the Builder object.
         */
        public Builder yearFirstPublished(int yearFirstPublished)
        {
            this.yearFirstPublished = yearFirstPublished;
            return this;
        }

        /**
         * Builds the author object from the fields currently initialized.
         * @return The Author object.
         */
        public Author build()
        {
            Author author = new Author();
            author.name = this.name;
            author.birthYear = this.birthYear; // will be 0 if not specified
            author.deathYear = this.deathYear; // will be 0 if not specified
            author.yearFirstPublished = this.yearFirstPublished; // will be 0 if not specified

            return author;
        }
    }

    //author object fields
    private String name;
    private int birthYear;
    private int deathYear;
    private int yearFirstPublished;
    private int yearsActive;

    /**
     * Author constructor does nothing, everything is handled by Builder sub-class.
     */
    private Author()
    {

    }

    /**
     * Returns author name.
     * @return author name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns authors year of birth.
     * @return authors year of birth
     */
    public int getBirthYear()
    {
        return this.birthYear;
    }

    /**
     * Returns authors year of death, or 0 if author is alive.
     * @return authors year of death, or 0 if author is alive
     */
    public int getDeathYear()
    {
        return this.deathYear;
    }

    /**
     * Returns the year the authors first work was published.
     * @return year the authors first work was published
     */
    public int getYearFirstPublished()
    {
        return this.yearFirstPublished;
    }

    /**
     * Returns the number of years the author has been active.
     * @return int number of years author has been/was active
     */
    public int getYearsActive()
    {
        return yearsActive();
    }

    /**
     * Calculates years active from author death year (or current year if author is alive, i.e. deathYear != 0)
     * and the year author was first published.
     * @return number of years author has been active
     */
    private int yearsActive()
    {
        int active = 0;
        if (this.deathYear != 0) {
            active = (deathYear - yearFirstPublished);
        } else {
            active = (2019 - this.yearFirstPublished);
        }

        return active;
    }
}
