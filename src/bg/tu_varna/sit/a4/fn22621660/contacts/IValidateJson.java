package bg.tu_varna.sit.a4.fn22621660.contacts;

import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

/**
 * Interface defining the validation operation for JSON content.
 */
public interface IValidateJson
{
    /**
     * Validates the given JSON content.
     *
     * @param jsonContent the JSON content to validate.
     * @throws InvalidJsonException if the JSON content is invalid.
     */
    void validateJSON(String jsonContent) throws InvalidJsonException;
}
