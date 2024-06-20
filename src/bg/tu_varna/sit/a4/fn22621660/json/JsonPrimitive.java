package bg.tu_varna.sit.a4.fn22621660.json;

/**
 * The {@code JsonPrimitive} class represents a JSON primitive value, such as a string, number, boolean, or null.
 * It extends the {@code JsonValue} abstract class.
 */
public class JsonPrimitive  extends JsonValue
{
    private String value;

    /**
     * Constructs a {@code JsonPrimitive} with the specified value.
     *
     * @param value the string representation of the primitive value.
     */
    public JsonPrimitive(String value) {
        this.value = value;
    }

    /**
     * Converts this JSON primitive to its JSON string representation.
     *
     * @return a {@code String} representing this JSON primitive in JSON format.
     */
    @Override
    public String toJsonString() {
        return "\"" + value + "\"";
    }

    /**
     * Returns the string representation of this JSON primitive.
     *
     * @return the string representation of the value.
     */
    @Override
    public String toString() {
        return value;
    }
}
