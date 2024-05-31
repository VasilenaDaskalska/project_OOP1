package bg.tu_varna.sit.a4.fn22621660.services;

import bg.tu_varna.sit.a4.fn22621660.contacts.IJsonValidator;

/**
 * Implementation of the IValidateJson interface for JSON validation.
 */
public class JsonValidator implements IJsonValidator
{
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

    private int findClosingQuote(String json, int startIndex) throws Exception {
        for (int i = startIndex + 1; i < json.length(); i++) {
            if (json.charAt(i) == '"' && json.charAt(i - 1) != '\\') {
                return i;
            }
        }
        throw new Exception("No matching closing quote for '\"' starting at position " + startIndex);
    }
}
