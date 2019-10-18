import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISBNTest {

    private long isbn10;
    private long isbn13;
    private long sinus13;
    private long linear10;
    private long linear13;
    private long electric10;
    private long electric13;
    private ISBNChecker testISBNChecker;

    public ISBNTest()
    {
    }
    @BeforeEach
    public void setUp() {

        //ISBN-10
        isbn10 =    1292159049L;
        linear10 =  1292092238L;
        //electric10 = 0_132_066_920L;

        //ISBN-13
        isbn13 =     9781292159041L;
        sinus13 =    9788202509057L;
        linear13 =   9781292092232L;
        electric13 = 9780132066921L;

        testISBNChecker = new ISBNChecker();
    }

    @Test
    public void test10to13()
    {
        assertEquals(isbn13, testISBNChecker.convert10to13(isbn10));
        assertEquals(linear13, testISBNChecker.convert10to13(linear10));
    }

    @Test
    public void testVerifyISBN()
    {
        assertTrue(testISBNChecker.verifyISBN(linear10));
        assertTrue(testISBNChecker.verifyISBN(isbn10));
        assertTrue(testISBNChecker.verifyISBN(isbn13));
        assertTrue(testISBNChecker.verifyISBN(sinus13));
        assertTrue(testISBNChecker.verifyISBN(linear13));
        assertTrue(testISBNChecker.verifyISBN(electric13));
    }

    @Test
    public void test10CheckDigit()
    {
        assertEquals(9, testISBNChecker.calculateISBN10CheckDigit(testISBNChecker.ISBNLongToIntArrayList(isbn10)));

        assertEquals(8, testISBNChecker.calculateISBN10CheckDigit(testISBNChecker.ISBNLongToIntArrayList(linear10)));

    }
}