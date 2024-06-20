package bg.tu_varna.sit.a4.fn22621660.json;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonValue
{
    private List<JsonValue> list;

    public JsonArray() {
        list = new ArrayList<>();
    }

    public void add(JsonValue value) {
        list.add(value);
    }

    public JsonValue get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i).toJsonString());
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
