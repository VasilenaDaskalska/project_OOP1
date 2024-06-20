package bg.tu_varna.sit.a4.fn22621660.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The {@code JsonObject} class represents a JSON object. It extends the {@code JsonValue} abstract class.
 * It allows storing key-value pairs where values can be various JSON types ({@code JsonValue} instances).
 */
public class JsonObject  extends JsonValue
{
    private Map<String, JsonValue> map;

    /**
     * Constructs an empty {@code JsonObject}.
     */
    public JsonObject() {
        map = new HashMap<>();
    }

    /**
     * Adds a key-value pair to this JSON object.
     *
     * @param key the key for the JSON property.
     * @param value the value as a {@code JsonValue} object.
     */
    public void put(String key, JsonValue value) {
        map.put(key, value);
    }

    /**
     * Retrieves the value associated with the specified key from this JSON object.
     *
     * @param key the key whose associated value is to be retrieved.
     * @return the {@code JsonValue} associated with the specified key, or {@code null} if the key is not present.
     */
    public JsonValue get(String key) {
        return map.get(key);
    }

    /**
     * Returns a {@code Set} view of the keys contained in this JSON object.
     *
     * @return a set view of the keys contained in this map.
     */
    public Set<String> keySet() {
        return map.keySet();
    }

    /**
     * Removes the key-value pair with the specified key from this JSON object.
     *
     * @param key the key whose mapping is to be removed from the map.
     */
    public void remove(String key) {
        map.remove(key);
    }

    /**
     * Converts this JSON object to its JSON string representation.
     *
     * @return a {@code String} representing this JSON object in JSON format.
     */
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
