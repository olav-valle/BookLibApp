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

    public String getStringInput()
    {
	System.out.print("> ");         // print prompt
	
	//get user input as String
        //separate input String into individual words
	return reader.nextLine();
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
