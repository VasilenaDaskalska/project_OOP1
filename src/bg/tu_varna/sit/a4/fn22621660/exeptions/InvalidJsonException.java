package bg.tu_varna.sit.a4.fn22621660.exeptions;

/**
 * Exception thrown when JSON validation fails.
 */
public class InvalidJsonException extends Exception
{
    /**
     * Constructs a new InvalidJsonException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidJsonException(String message) {
        super(message);
    }
}
