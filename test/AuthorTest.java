import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    Author author;

    @BeforeEach
    void setUp() {
        author = new Author.Builder("Terry Pratchett")
                .birthYear(1948)
                .deathYear(2015)
                .yearFirstPublished(1971)
                .build();
    }


    @Test
    void getName() {
        assertEquals("Terry Pratchett", author.getName());
    }

    @Test
    void getBirthYear() {
        assertEquals(1948, author.getBirthYear());
    }

    @Test
    void getDeatYear() {
        assertEquals(2015, author.getDeathYear());
    }

    @Test
    void getYearFirstPublished() {
        assertEquals(1971, author.getYearFirstPublished());
    }

    @Test
    void getYearsActive()
    {
        assertEquals(44, author.getYearsActive());
    }
}