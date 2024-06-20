package bg.tu_varna.sit.a4.fn22621660.json;

/**
 * The {@code JsonNull} class represents a JSON null value. It extends the {@code JsonValue} abstract class.
 */
public class JsonNull extends JsonValue
{
    /**
     * Converts this JSON null value to its JSON string representation.
     *
     * @return the string "null", representing the JSON null value.
     */
    @Override
    public String toJsonString() {
        return "null";
    }
}
