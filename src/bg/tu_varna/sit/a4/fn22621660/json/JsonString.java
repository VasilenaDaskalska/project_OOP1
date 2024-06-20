package bg.tu_varna.sit.a4.fn22621660.json;

public class JsonString extends JsonValue
{
    private String value;

    public JsonString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toJsonString() {
        return "\"" + value + "\"";
    }
}
