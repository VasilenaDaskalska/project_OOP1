package bg.tu_varna.sit.a4.fn22621660.json;

/**
 * The {@code JsonBoolean} class represents a JSON boolean value. It extends the {@code JsonValue} abstract class.
 */
public class JsonBoolean extends JsonValue
{
    private Boolean value;

    /**
     * Constructs a {@code JsonBoolean} with the specified boolean value.
     *
     * @param value the boolean value for this JSON boolean.
     */
    public JsonBoolean(Boolean value) {
        this.value = value;
    }

    /**
     * Returns the boolean value of this JSON boolean.
     *
     * @return the boolean value.
     */
    public Boolean getValue() {
        return value;
    }

    /**
     * Converts this JSON boolean to its JSON string representation.
     *
     * @return a {@code String} representing this JSON boolean in JSON format.
     */
    @Override
    public String toJsonString() {
        return value.toString();
    }
}
