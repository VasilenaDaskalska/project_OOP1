package bg.tu_varna.sit.a4.fn22621660.services;

import bg.tu_varna.sit.a4.fn22621660.contacts.IJsonValidator;

/**
 * The {@code JsonValidator} class provides an implementation of the {@code IJsonValidator} interface.
 * It includes methods to validate if a given string is a well-formed JSON.
 */
public class JsonValidator implements IJsonValidator
{
    /**
     * Validates if the given JSON string is well-formed.
     *
     * @param json the JSON string to validate.
     * @return {@code true} if the JSON string is valid, {@code false} otherwise.
     */
    public boolean isValidJson(String json) {
        json = json.trim();
        if ((json.startsWith("{") && json.endsWith("}")) || (json.startsWith("[") && json.endsWith("]"))) {
            try {
                validateJson(json);
                return true;
            } catch (Exception e) {
                System.out.println("Validation error: " + e.getMessage());
                return false;
            }
        }
        System.out.println("Invalid JSON format: JSON must start with '{' or '[' and end with '}' or ']'.");
        return false;
    }

    /**
     * Validates the structure of the JSON string by checking matching brackets and quotes.
     *
     * @param json the JSON string to validate.
     * @throws Exception if the JSON string is not well-formed.
     */
    private void validateJson(String json) throws Exception {
        int length = json.length();
        int index = 0;
        while (index < length) {
            char currentChar = json.charAt(index);
            if (currentChar == '{' || currentChar == '[') {
                index = findClosingBracket(json, index);
            } else if (currentChar == '}' || currentChar == ']') {
                throw new Exception("Unexpected closing bracket '" + currentChar + "' at position " + index);
            } else if (currentChar == '"') {
                index = findClosingQuote(json, index);
            }
            index++;
        }
    }

    /**
     * Finds the position of the matching closing bracket for the given opening bracket.
     *
     * @param json the JSON string.
     * @param startIndex the index of the opening bracket.
     * @return the index of the matching closing bracket.
     * @throws Exception if no matching closing bracket is found.
     */
    private int findClosingBracket(String json, int startIndex) throws Exception {
        char openBracket = json.charAt(startIndex);
        char closeBracket = openBracket == '{' ? '}' : ']';
        int depth = 1;
        for (int i = startIndex + 1; i < json.length(); i++) {
            char currentChar = json.charAt(i);
            if (currentChar == openBracket) {
                depth++;
            } else if (currentChar == closeBracket) {
                depth--;
                if (depth == 0) {
                    return i;
                }
            } else if (currentChar == '"') {
                i = findClosingQuote(json, i);
            }
        }
        throw new Exception("No matching closing bracket for '" + openBracket + "' starting at position " + startIndex);
    }

    /**
     * Finds the position of the closing quote for the given opening quote.
     *
     * @param json the JSON string.
     * @param startIndex the index of the opening quote.
     * @return the index of the matching closing quote.
     * @throws Exception if no matching closing quote is found.
     */
    private int findClosingQuote(String json, int startIndex) throws Exception {
        for (int i = startIndex + 1; i < json.length(); i++) {
            if (json.charAt(i) == '"' && json.charAt(i - 1) != '\\') {
                return i;
            }
        }
        throw new Exception("No matching closing quote for '\"' starting at position " + startIndex);
    }
}
