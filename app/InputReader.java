import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;

/**
 * InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is returned.
 * 
 * @author     Olav Valle
 * @version    0.2
 */
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
     * and return it as a String.
     *
     * @return  A String typed by the user.
     */
    public HashSet<String>  getStringInputAsSet()
    {
        System.out.print("> ");         // print prompt
	
	//get user input as String
	String inputLine = reader.nextLine();
	//separate input String into individual words
	String[] wordsAsArray = inputLine.split(" ");
	//collect word Strings in a Set
	HashSet<String> wordSet = new HashSet<>(Arrays.asList(wordsAsArray));
        return (wordSet); //return Set of user input words
    }

    public String getStringInput()
    {
	System.out.print("> ");         // print prompt
	
	//get user input as String
	String inputLine = reader.nextLine();
	//separate input String into individual words
	return inputLine;
    }

    /**
    * Read integer input from stdin, and returns it to caller.
    *
    *@return An integer typed by user.
    */
    public int getIntInput() 
    {
	
        System.out.print("> ");         // print prompt
        int inputInt = reader.nextInt();
	reader.nextLine(); //solves problem of leaving a line break behind
        return inputInt;
    }
}
