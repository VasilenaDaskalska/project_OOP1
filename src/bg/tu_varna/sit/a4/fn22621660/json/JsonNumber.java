package bg.tu_varna.sit.a4.fn22621660.json;

public class JsonNumber extends JsonValue
{
    private Number value;

    public JsonNumber(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public String toJsonString() {
        return value.toString();
    }
}
