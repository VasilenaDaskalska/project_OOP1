package bg.tu_varna.sit.a4.fn22621660.json;

public class JsonBoolean extends JsonValue
{
    private Boolean value;

    public JsonBoolean(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toJsonString() {
        return value.toString();
    }
}
