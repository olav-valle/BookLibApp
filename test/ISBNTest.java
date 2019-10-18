import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ISBNTest<ISBN10> {

    private long ISBN10;
    private long ISBN13;
    private ISBNChecker testISBNChecker;

    public ISBNTest()
    {
    }
    @BeforeEach
    public void setUp() {
        ISBN10 = 1292159049L;
        ISBN13 = 9781292159041L;
        testISBNChecker = new ISBNChecker();
    }

    @Test
    public void test10to13()
    {
        assertEquals(ISBN13, testISBNChecker.convert10to13(ISBN10));
    }
}