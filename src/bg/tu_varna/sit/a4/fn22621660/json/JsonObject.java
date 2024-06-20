package bg.tu_varna.sit.a4.fn22621660.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonObject  extends JsonValue
{
    private Map<String, JsonValue> map;

    public JsonObject() {
        map = new HashMap<>();
    }

    public void put(String key, JsonValue value) {
        map.put(key, value);
    }

    public JsonValue get(String key) {
        return map.get(key);
    }

    public Set<String> keySet() {
        return map.keySet();
    }


    public void remove(String key) {
        map.remove(key);
    }

    @Override
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = map.size();
        int count = 0;
        for (Map.Entry<String, JsonValue> entry : map.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":").append(entry.getValue().toJsonString());
            if (count < size - 1) {
                sb.append(",");
            }
            count++;
        }
        sb.append("}");
        return sb.toString();
    }
}
