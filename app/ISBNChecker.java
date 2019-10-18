import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Handles testing and converting of ISBN numbers.
 * @author Olav Valle
 * @version 2019/10/18
 */
public class ISBNChecker {

    Integer[] prefix = {9, 7, 8};

    /**
     * Constructor.
     */
    public ISBNChecker()
    {
    }

    /**
     * Converts an ISBN-10 number to a valid ISBN-13.
     * @param isbn10 10 digit ISBN number to convert to 13 digit
     * @return the ISBN-13 number that corresponds to the provided ISBN-10.
     */
    public long convert10to13(long isbn10)
    {
        long ISBN13;
        long ISBN10 = isbn10;

        String ISBNString = Long.toString(isbn10);
        String[] ISBNArray = ISBNString.split("");

        ArrayList<Integer> intList = (Arrays.asList(ISBNArray)).stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
        intList.remove(9);
        intList.addAll(0, Arrays.asList(prefix));

        //check digit
        int checkDigit13 = (10 - ((intList.get(0) + 3*intList.get(1) + intList.get(2) + 3*intList.get(3) + intList.get(4) + 3*intList.get(5)
        + intList.get(6) + 3*intList.get(7) + intList.get(8) + 3*intList.get(9) + intList.get(10) + 3*intList.get(11))) % 10);

        intList.add(checkDigit13);

        String longString = intList.stream().map(i -> Integer.toString(i))
                .reduce("", (a, b) -> a + b);

        return Long.parseLong(longString);

    }
}
