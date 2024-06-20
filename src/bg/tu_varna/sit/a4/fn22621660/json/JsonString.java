package bg.tu_varna.sit.a4.fn22621660.json;

/**
 * The {@code JsonString} class represents a JSON string value. It extends the {@code JsonValue} abstract class.
 */
public class JsonString extends JsonValue
{
    private String value;

    /**
     * Constructs a {@code JsonString} with the specified value.
     *
     * @param value the string value for this JSON string.
     */
    public JsonString(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of this JSON string.
     *
     * @return the string value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Converts this JSON string to its JSON string representation.
     *
     * @return a {@code String} representing this JSON string in JSON format.
     */
    @Override
    public String toJsonString() {
        return "\"" + value + "\"";
    }
}
