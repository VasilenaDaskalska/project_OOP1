package bg.tu_varna.sit.a4.fn22621660.contacts;

/**
 * The {@code IJsonValidator} interface provides a method to validate JSON strings.
 * Implementations of this interface should define the logic to check if a JSON string is valid.
 */
public interface IJsonValidator
{
    /**
     * Validates the given JSON string.
     *
     * @param json the JSON string to validate.
     * @return {@code true} if the JSON string is valid, {@code false} otherwise.
     */
    boolean isValidJson(String json);
}
