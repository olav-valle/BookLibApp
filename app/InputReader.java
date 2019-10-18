import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;

/**
 * InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is returned.
 * 
 * @author     Olav Valle
 * @version    2019/10/17
 */

//TODO How do I test a Scanner?
@SuppressWarnings("WeakerAccess")
public class InputReader
{
    private Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Read a line of text from standard input (the text terminal),
     * separate the individual words, and return them as a HashSet.
     * @return  HashSet containing the words the user has typed.
     */
    public HashSet<String> getStringInputAsSet()
    {
        System.out.print("> ");         // print prompt
	
	    //get user input as String
	    String inputLine = this.reader.nextLine().toLowerCase();
	    //separate input String into individual words
	    String[] wordsAsArray = inputLine.split(" ");
	    //return a set of word Strings
        return (new HashSet<>(Arrays.asList(wordsAsArray)));
    }

    /**
     * Returns user input as a String.
     * @return user input String.
     */
    public String getStringInput()
    {
	    System.out.print("> ");         // print prompt
	    return reader.nextLine();
    }

    /**
    * Returns user input as integer value, or 0 if user input is not int.
    *@return An integer typed by user, or 0 if input is not int.
    */
    public int getIntInput() 
    {
        int inputInt;
        System.out.print("> ");         // print prompt

        if(!(reader.hasNextInt())) { inputInt = 0; }
        else { inputInt = reader.nextInt(); }

        reader.nextLine();  //solves problem of leaving a line break behind
        return inputInt;
    }
}
