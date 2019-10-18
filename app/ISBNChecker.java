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

    Integer[] ISBN13prefix = {9, 7, 8};

    /**
     * Constructor.
     */
    public ISBNChecker() {
    }

    /**
     * Separates a long type number into individual integers, and returns them in an ArrayList.
     *
     * @param longNumber A number of primitive type long.
     * @return An ArrayList collecting the individual integer digits of longNumber.
     */
    public ArrayList<Integer> ISBNLongToIntArray(long longNumber) {
        return Arrays.stream(Long.toString(longNumber)
                .split("")).map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Converts an ISBN-10 number to a valid ISBN-13.
     *
     * @param isbn10 10 digit ISBN number to convert to 13 digit
     * @return the ISBN-13 number that corresponds to the provided ISBN-10.
     */
    public long convert10to13(long isbn10)
    {
        ArrayList<Integer> intList = ISBNLongToIntArray(isbn10); //convert the long to list of int

        intList.remove(9); //remove ISBN-10 check digit

        intList.addAll(0, asList(ISBN13prefix)); //prepend 978

        intList.add(calculateISBN13CheckDigit(intList)); // recieve and append ISBN-13 check digit

        return Long.parseLong(intList.stream() // map integers in ArrayList to strings,
                .map(i -> Integer.toString(i)) // reduce them to a single string,
                .reduce("", (a, b) -> a + b)); // parse the string as a long type number
        // and return the completed ISBN-13
    }

    /**
     * Calculates the check digit of a 13 digit ISBN number using the first 12 digits.
     *
     * @param first12Digits An ArrayList of the first 12 digits in an ISBN-13 number.
     * @return The calculated check digit.
     */
    private int calculateISBN13CheckDigit(ArrayList<Integer> first12Digits)
    {
        int checkDigit13 = (10 - ((first12Digits.get(0) + 3 * first12Digits.get(1) + first12Digits.get(2) + 3 * first12Digits.get(3) + first12Digits.get(4) + 3 * first12Digits.get(5)
                + first12Digits.get(6) + 3 * first12Digits.get(7) + first12Digits.get(8) + 3 * first12Digits.get(9) + first12Digits.get(10) + 3 * first12Digits.get(11))) % 10);
        return checkDigit13;
    }

}
