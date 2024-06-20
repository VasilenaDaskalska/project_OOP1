package bg.tu_varna.sit.a4.fn22621660.json;

/**
 * The {@code JsonFormat} class provides methods to format JSON strings with indentation.
 */
public class JsonFormat
{
    /**
     * Formats the given JSON string with the specified indent width.
     *
     * @param jsonString the JSON string to format.
     * @param indentWidth the width of each indentation level.
     * @return the formatted JSON string with proper indentation.
     */
    public String formatJson(String jsonString, int indentWidth) {
        StringBuilder formattedJson = new StringBuilder();
        String indent = createIndent(indentWidth);
        int indentLevel = 0;
        boolean inString = false;

        for (int i = 0; i < jsonString.length(); i++) {
            char currentChar = jsonString.charAt(i);

            if (currentChar == '"') {
                formattedJson.append(currentChar);
                inString = !inString;
                continue;
            }

            if (!inString) {
                switch (currentChar) {
                    case '{':
                    case '[':
                        formattedJson.append(currentChar).append("\n");
                        indentLevel++;
                        formattedJson.append(createIndent(indentLevel * indentWidth));
                        break;
                    case '}':
                    case ']':
                        formattedJson.append("\n");
                        indentLevel--;
                        formattedJson.append(createIndent(indentLevel * indentWidth)).append(currentChar);
                        break;
                    case ',':
                        formattedJson.append(currentChar).append("\n").append(createIndent(indentLevel * indentWidth));
                        break;
                    case ':':
                        formattedJson.append(currentChar).append(" ");
                        break;
                    default:
                        if (!Character.isWhitespace(currentChar)) {
                            formattedJson.append(currentChar);
                        }
                        break;
                }
            } else {
                formattedJson.append(currentChar);
            }
        }

        return formattedJson.toString();
    }

    /**
     * Creates an indentation string of the specified width.
     *
     * @param width the width of the indentation.
     * @return a string consisting of spaces with the specified width.
     */
    public String createIndent(int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
