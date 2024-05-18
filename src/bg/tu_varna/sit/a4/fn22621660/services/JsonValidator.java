package bg.tu_varna.sit.a4.fn22621660.services;

import bg.tu_varna.sit.a4.fn22621660.contacts.IJsonValidator;
import bg.tu_varna.sit.a4.fn22621660.contacts.IJsonValidator;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

/**
 * Implementation of the IValidateJson interface for JSON validation.
 */
public class JsonValidator implements IJsonValidator
{
    /**
     * Validates the syntax of a JSON string.
     *
     * @param jsonContent The JSON string to validate.
     * @throws InvalidJsonException If the JSON syntax is invalid.
     */
    @Override
    public void validateJSON(String jsonContent) throws InvalidJsonException {
        int curlyBraces = 0;
        int squareBrackets = 0;
        boolean inQuotes = false;

        for (int i = 0; i < jsonContent.length(); i++) {
            char c = jsonContent.charAt(i);

            if (c == '{' && !inQuotes) {
                curlyBraces++;
            } else if (c == '}' && !inQuotes) {
                curlyBraces--;
                if (curlyBraces < 0) {
                    throw new InvalidJsonException("Error: Incorrect closing of braces.");
                }
            } else if (c == '[' && !inQuotes) {
                squareBrackets++;
            } else if (c == ']' && !inQuotes) {
                squareBrackets--;
                if (squareBrackets < 0) {
                    throw new InvalidJsonException("Error: Incorrect closing of square brackets.");
                }
            }
            else if (c == '"') {
                inQuotes = !inQuotes;
            }
        }
    }
}
