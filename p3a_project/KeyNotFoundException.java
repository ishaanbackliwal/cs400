/**
 * Checked exception thrown when a non existent key is specified for get or remove.
 * DO NOT EDIT THIS CLASS
 */
@SuppressWarnings("serial")
public class KeyNotFoundException extends Exception {

    /**
     * default no-arg constructor
     */
    public KeyNotFoundException() { }

    /**
     * This constructor is provided to allow user to include a message
     * @param msg Additional message for this exception
     */
    public KeyNotFoundException(String msg) { 
        super(msg);
    }

}

