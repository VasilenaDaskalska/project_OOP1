package bg.tu_varna.sit.a4.fn22621660.json;

public class JsonPrimitive  extends JsonValue
{
    private String value;

    public JsonPrimitive(String value) {
        this.value = value;
    }

    @Override
    public String toJsonString() {
        return "\"" + value + "\"";
    }

    @Override
    public String toString() {
        return value;
    }
}
