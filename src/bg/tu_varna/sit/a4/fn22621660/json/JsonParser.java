package bg.tu_varna.sit.a4.fn22621660.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser
{
    public static JsonObject parse(String json)
    {
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}"))
        {
            JsonObject jsonObject = new JsonObject();
            json = json.substring(1, json.length() - 1).trim();
            String[] pairs = json.split(",");
            for (String pair : pairs) {
                String[] keyValue = pair.split(":", 2);
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().replaceAll("^\"|\"$", "");
                    String value = keyValue[1].trim();
                    jsonObject.put(key, new JsonPrimitive(value.replaceAll("^\"|\"$", "")));
                }
            }
            return jsonObject;
        }
        return null;
    }

    public static JsonObject parseFile(String filePath) throws IOException {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return parse(content);
    }
}
