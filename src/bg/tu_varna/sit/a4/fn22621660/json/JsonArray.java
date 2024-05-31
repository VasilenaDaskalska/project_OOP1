package bg.tu_varna.sit.a4.fn22621660.json;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonValue
{
    private List<JsonValue> list;

    public JsonArray() {
        this.list = new ArrayList<>();
    }

    public void add(JsonValue value) {
        list.add(value);
    }

    public JsonValue get(int index) {
        return list.get(index);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (JsonValue value : list) {
            if (!first) {
                sb.append(",");
            }
            sb.append(value.toJsonString());
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return toJsonString();
    }
}
