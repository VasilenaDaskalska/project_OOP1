package bg.tu_varna.sit.a4.fn22621660.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonObject  extends JsonValue
{
    private Map<String, JsonValue> map;

    public JsonObject() {
        this.map = new HashMap<>();
    }

    public JsonValue get(String key) {
        return map.get(key);
    }

    public void put(String key, JsonValue value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (Map.Entry<String, JsonValue> entry : map.entrySet()) {
            if (!first) {
                sb.append(",");
            }
            sb.append("\"").append(entry.getKey()).append("\":").append(entry.getValue().toJsonString());
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return toJsonString();
    }
}
