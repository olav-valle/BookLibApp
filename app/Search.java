import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Contains methods for retrieving and calling all accessor methods contained 
 * in an object. 
 * 
 * Very early and inelegant implementation, which simply calls any method with "get"
 * in its name. This assumes that only accessor methods contain "get" in their names,
 * and also that all of these methods are desirable to call.
 * 
 * Use with caution, as modularisation is far from complete.
 * 
 * 
 * @author Olav Valle
 * @version 0.1-20190928
 */
public class Search
{
    // instance variables - replace the example below with your own
    private Method[] methods;
    private Book book;

    /**
     * Constructor for objects of class Search
     */
    public Search() //throws  IllegalAccessException, InvocationTargetException
    {
        book = new Book("title", "author", "publisher", "date", "pages", "ean13");
        // invokeAllMethods(book); 
        // uncomment to call main function upon creation
    }

    /**
     * method to collect all methods declared in the class of an object.
     *
     * @param  obj the object whose methods should be collected
     * 
     */
    public void makeMethodArray(Object obj)
    {
        this.methods = obj.getClass().getDeclaredMethods();

    }
    
    /**
     * invokes each of the "get" methods belonging to the Object.
     * Obvious warning is that ANY method with "get" in name will be called,
     * even if it is not an accessor method.
     * @param obj Object whoose methods are to be called
     * @return
     */
    public static Object invokeAllMethods(Object obj) throws IllegalAccessException, InvocationTargetException
    {
        //Arrays.stream(methods).forEach(m -> System.out.println(m.invoke(obj)));
        Method[] met = obj.getClass().getDeclaredMethods();
        for(Method m : met) {
            if (m.getName().contains("get")) {
                try {
                    return (m.invoke(obj));
                    //System.out.println(m.invoke(obj));
                } catch (InvocationTargetException e) {
                    System.out.println("InvocationTargetException : method being invoked probably threw an exception? I don't know...");
                    throw e;
                } catch (IllegalAccessException e) {
                    System.out.println("InvocationTargetException : method being invoked probably threw an exception? I don't know...");
                    throw e;
                }
            }
        }
        return obj;
    }
}
