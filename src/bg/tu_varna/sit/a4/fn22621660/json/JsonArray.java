package bg.tu_varna.sit.a4.fn22621660.json;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code JsonArray} class represents a JSON array. It extends the {@code JsonValue} abstract class.
 * It allows storing an ordered collection of {@code JsonValue} instances.
 */
public class JsonArray extends JsonValue
{
    private List<JsonValue> list;

    /**
     * Constructs an empty {@code JsonArray}.
     */
    public JsonArray() {
        list = new ArrayList<>();
    }

    /**
     * Adds a {@code JsonValue} to the end of this JSON array.
     *
     * @param value the {@code JsonValue} to add.
     */
    public void add(JsonValue value) {
        list.add(value);
    }

    /**
     * Retrieves the {@code JsonValue} at the specified index in this JSON array.
     *
     * @param index the index of the {@code JsonValue} to retrieve.
     * @return the {@code JsonValue} at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public JsonValue get(int index) {
        return list.get(index);
    }

    /**
     * Returns the number of {@code JsonValue} elements in this JSON array.
     *
     * @return the number of elements in this array.
     */
    public int size() {
        return list.size();
    }

    /**
     * Converts this JSON array to its JSON string representation.
     *
     * @return a {@code String} representing this JSON array in JSON format.
     */
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
