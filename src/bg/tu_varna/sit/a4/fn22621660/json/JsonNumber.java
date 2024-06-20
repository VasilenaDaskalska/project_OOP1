package bg.tu_varna.sit.a4.fn22621660.json;

/**
 * The {@code JsonNumber} class represents a JSON number value. It extends the {@code JsonValue} abstract class.
 */
public class JsonNumber extends JsonValue
{
    private Number value;

    /**
     * Constructs a {@code JsonNumber} with the specified numeric value.
     *
     * @param value the numeric value for this JSON number.
     */
    public JsonNumber(Number value) {
        this.value = value;
    }

    /**
     * Returns the numeric value of this JSON number.
     *
     * @return the numeric value.
     */
    public Number getValue() {
        return value;
    }

    /**
     * Converts this JSON number to its JSON string representation.
     *
     * @return a {@code String} representing this JSON number in JSON format.
     */
    @Override
    public String toJsonString() {
        return value.toString();
    }
}
