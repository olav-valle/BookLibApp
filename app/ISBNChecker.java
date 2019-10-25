import java.lang.reflect.Array;
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

    private static final Integer[] ISBN13prefix = {9, 7, 8}; //Bookland UCC

    /**
     * Constructor.
     */
    public ISBNChecker() {

    }

    /**
     * Converts an ISBN-10 number to a valid ISBN-13.
     *
     * @param isbn10 10 digit ISBN number to convert to 13 digit
     * @return the ISBN-13 number that corresponds to the provided ISBN-10.
     */
    public long convert10to13(long isbn10)
    {
        ArrayList<Integer> intList = ISBNLongToIntArrayList(isbn10); //convert the long to list of int

        intList.remove(9); //remove ISBN-10 check digit

        intList.addAll(0, asList(ISBN13prefix)); //prepend 978

        intList.add(calculateISBN13CheckDigit(intList)); // recieve and append ISBN-13 check digit

        return Long.parseLong(intList.stream() // map integers in ArrayList to strings,
                .map(i -> Integer.toString(i)) // reduce them to a single string,
                .reduce("", (a, b) -> a + b)); // parse the string as a long type number
        // and return the completed ISBN-13
    }

    /**
     * Verifies an ISBN number by testing its check digit.
     * Compares the check digit (the last digit) of the ISBN number, to the digit calculated from the check digit algorithm.
     * @param isbn An ISBN number of type long.
     * @return true if number is a valid ISBN, false if not.
     */
    public boolean verifyISBN(long isbn)
    {
        boolean valid = false;

        ArrayList<Integer> isbnAsInt = ISBNLongToIntArrayList(isbn);

        if(isbnAsInt.size() == 13) {
            valid = (isbnAsInt.get(12) == calculateISBN13CheckDigit(isbnAsInt));
        }
        else if(isbnAsInt.size() == 10) {
            valid = (isbnAsInt.get(9) == calculateISBN10CheckDigit(isbnAsInt));
        //TODO implement ISBN-10 check digit algorithm
        }
        else if (isbnAsInt.size() == 9) {
            isbnAsInt.add(0, 0);
            // prefix a 9 digit ISBN with 0 to make ISBN-10
            valid = (isbnAsInt.get(9) == calculateISBN10CheckDigit(isbnAsInt));
        }

        return valid;
    }

    /**
     * Separates a long type number into individual integers, and returns them in an ArrayList.
     *
     * @param longNumber A number of primitive type long.
     * @return An ArrayList collecting the individual integer digits of longNumber.
     */
    public ArrayList<Integer> ISBNLongToIntArrayList(long longNumber) {
        return Arrays.stream(Long.toString(longNumber)
                .split("")).map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Calculates the check digit of a 13 digit ISBN number using the first 12 digits.
     *
     * @param isbn13Ints An ArrayList of the first 12 digits in an ISBN-13 number.
     * @return The calculated check digit.
     */
    private int calculateISBN13CheckDigit(ArrayList<Integer> isbn13Ints)
    {
        int checkDigit13 = (10 - ((isbn13Ints.get(0) + 3 * isbn13Ints.get(1) + isbn13Ints.get(2) + 3 * isbn13Ints.get(3) + isbn13Ints.get(4) + 3 * isbn13Ints.get(5)
                + isbn13Ints.get(6) + 3 * isbn13Ints.get(7) + isbn13Ints.get(8) + 3 * isbn13Ints.get(9) + isbn13Ints.get(10) + 3 * isbn13Ints.get(11))) % 10);
        return checkDigit13;
    }

    /**
     * Calculates the check digit of a 10 digit ISBN number using the first 9 digits.
     * @param isbn10Ints An array of the first 9 digits in the
     * @return The calculated check digit.
     */
    public int calculateISBN10CheckDigit(ArrayList<Integer> isbn10Ints)
    {
        int sum = 0;
        for(int i = 0; i < 9; i++){
            sum += ((10-i) * isbn10Ints.get(i));
        }
        return (11 - (sum % 11) ) % 11;


    }
}
