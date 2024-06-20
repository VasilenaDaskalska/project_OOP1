package bg.tu_varna.sit.a4.fn22621660.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser
{
    private int index;
    private String json;

    public JsonObject parseObject(String json) throws Exception {
        this.json = json;
        this.index = 0;
        skipWhitespace();
        if (json.charAt(index) != '{') {
            throw new Exception("Invalid JSON object");
        }
        index++;
        return parseJsonObject();
    }

    private JsonObject parseJsonObject() throws Exception {
        JsonObject jsonObject = new JsonObject();
        skipWhitespace();
        while (index < json.length() && json.charAt(index) != '}') {
            String key = parseString();
            skipWhitespace();
            if (index >= json.length() || json.charAt(index) != ':') {
                throw new Exception("Expected ':' after key");
            }
            index++;
            skipWhitespace();
            JsonValue value = parseValue();
            jsonObject.put(key, value);
            skipWhitespace();
            if (index < json.length() && json.charAt(index) == ',') {
                index++;
                skipWhitespace();
            }
        }
        if (index >= json.length() || json.charAt(index) != '}') {
            throw new Exception("Expected '}' at the end of JSON object");
        }
        index++;
        return jsonObject;
    }

    private JsonArray parseJsonArray() throws Exception {
        JsonArray jsonArray = new JsonArray();
        skipWhitespace();
        while (index < json.length() && json.charAt(index) != ']') {
            JsonValue value = parseValue();
            jsonArray.add(value);
            skipWhitespace();
            if (index < json.length() && json.charAt(index) == ',') {
                index++;
                skipWhitespace();
            }
        }
        if (index >= json.length() || json.charAt(index) != ']') {
            throw new Exception("Expected ']' at the end of JSON array");
        }
        index++;
        return jsonArray;
    }

    private JsonValue parseValue() throws Exception {
        skipWhitespace();
        if (index >= json.length()) {
            throw new Exception("Unexpected end of JSON input");
        }
        char current = json.charAt(index);
        if (current == '"') {
            return new JsonString(parseString());
        } else if (current == '{') {
            index++;
            return parseJsonObject();
        } else if (current == '[') {
            index++;
            return parseJsonArray();
        } else if (current == 't' || current == 'f') {
            return new JsonBoolean(parseBoolean());
        } else if (current == 'n') {
            return parseNull();
        } else if (isDigit(current) || current == '-') {
            return new JsonNumber(parseNumber());
        } else {
            throw new Exception("Unexpected character: " + current);
        }
    }

    private String parseString() throws Exception {
        StringBuilder sb = new StringBuilder();
        index++; // Skip the opening quote
        while (index < json.length() && json.charAt(index) != '"') {
            char current = json.charAt(index);
            if (current == '\\') {
                index++;
                if (index >= json.length()) {
                    throw new Exception("Invalid escape sequence at end of string");
                }
                current = json.charAt(index);
                switch (current) {
                    case '"':
                    case '\\':
                    case '/':
                        sb.append(current);
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        if (index + 4 >= json.length()) {
                            throw new Exception("Invalid unicode escape sequence");
                        }
                        String hex = json.substring(index + 1, index + 5);
                        sb.append((char) Integer.parseInt(hex, 16));
                        index += 4;
                        break;
                    default:
                        throw new Exception("Invalid escape sequence: \\" + current);
                }
            } else {
                sb.append(current);
            }
            index++;
        }
        if (index >= json.length() || json.charAt(index) != '"') {
            throw new Exception("Expected closing quote for string");
        }
        index++; // Skip the closing quote
        return sb.toString();
    }

    private boolean parseBoolean() throws Exception {
        if (json.startsWith("true", index)) {
            index += 4;
            return true;
        } else if (json.startsWith("false", index)) {
            index += 5;
            return false;
        } else {
            throw new Exception("Invalid boolean value");
        }
    }

    private JsonNull parseNull() throws Exception {
        if (json.startsWith("null", index)) {
            index += 4;
            return new JsonNull();
        } else {
            throw new Exception("Invalid null value");
        }
    }

    private Number parseNumber() throws Exception {
        int startIndex = index;
        if (json.charAt(index) == '-') {
            index++;
        }
        while (index < json.length() && isDigit(json.charAt(index))) {
            index++;
        }
        if (index < json.length() && json.charAt(index) == '.') {
            index++;
            while (index < json.length() && isDigit(json.charAt(index))) {
                index++;
            }
        }
        if (index < json.length() && (json.charAt(index) == 'e' || json.charAt(index) == 'E')) {
            index++;
            if (index < json.length() && (json.charAt(index) == '+' || json.charAt(index) == '-')) {
                index++;
            }
            while (index < json.length() && isDigit(json.charAt(index))) {
                index++;
            }
        }
        String numberString = json.substring(startIndex, index);
        if (numberString.contains(".") || numberString.contains("e") || numberString.contains("E")) {
            return Double.parseDouble(numberString);
        } else {
            return Integer.parseInt(numberString);
        }
    }

    private void skipWhitespace() {
        while (index < json.length() && Character.isWhitespace(json.charAt(index))) {
            index++;
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
