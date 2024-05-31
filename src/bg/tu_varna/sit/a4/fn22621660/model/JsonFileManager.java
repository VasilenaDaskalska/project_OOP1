package bg.tu_varna.sit.a4.fn22621660.model;

import bg.tu_varna.sit.a4.fn22621660.commands.*;
import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonObject;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JsonFileManager {
    private JsonObject rootNode;
    private String currentFilePath;
    private Map<String, ICommand> commandMap;

    public JsonFileManager() {
        commandMap = new HashMap<>();
        commandMap.put("open", new OpenCommand(this));
        commandMap.put("close", new CloseCommand(this));
        commandMap.put("save", new SaveCommand(this));
        commandMap.put("saveas", new SaveAsCommand(this));
        commandMap.put("print", new PrintCommand(this));
        commandMap.put("search", new SearchCommand(this));
        commandMap.put("set", new SetCommand(this));
        commandMap.put("create", new CreateCommand(this));
        commandMap.put("delete", new DeleteCommand(this));
        commandMap.put("move", new MoveCommand());
        commandMap.put("edit", new EditCommand(this));
        commandMap.put("help", new HelpCommand());
    }

    public void setRootNode(JsonObject rootNode) {
        this.rootNode = rootNode;
    }

    public JsonObject getRootNode() {
        return rootNode;
    }

    public void setCurrentFilePath(String currentFilePath) {
        this.currentFilePath = currentFilePath;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public JsonValue getNodeAtPath(String path) {
        String[] keys = path.split("\\.");
        JsonValue currentNode = rootNode;
        for (String key : keys) {
            if (currentNode instanceof JsonObject) {
                currentNode = ((JsonObject) currentNode).get(key);
            } else {
                return null;
            }
        }
        return currentNode;
    }

    public boolean setNodeAtPath(String path, JsonValue value) {
        String[] keys = path.split("\\.");

        if(rootNode == null)
        {
            rootNode = new JsonObject();
        }

        JsonValue currentNode = rootNode;

        for (int i = 0; i < keys.length - 1; i++) {
            if (currentNode instanceof JsonObject) {
                currentNode = ((JsonObject) currentNode).get(keys[i]);
            } else {
                return false;
            }
        }
        if (currentNode instanceof JsonObject) {
            ((JsonObject) currentNode).put(keys[keys.length - 1], value);
            return true;
        }
        return false;
    }

    public boolean createNodeAtPath(String path, JsonValue value) {
        String[] keys = path.split("\\.");
        JsonValue currentNode = rootNode;
        for (int i = 0; i < keys.length - 1; i++) {
            if (currentNode instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) currentNode;
                if (jsonObject.get(keys[i]) == null) {
                    jsonObject.put(keys[i], new JsonObject());
                }
                currentNode = jsonObject.get(keys[i]);
            } else {
                return false;
            }
        }
        if (currentNode instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) currentNode;
            if (jsonObject.get(keys[keys.length - 1]) == null) {
                jsonObject.put(keys[keys.length - 1], value);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNodeAtPath(String path) {
        String[] keys = path.split("\\.");
        JsonValue currentNode = rootNode;
        for (int i = 0; i < keys.length - 1; i++) {
            if (currentNode instanceof JsonObject) {
                currentNode = ((JsonObject) currentNode).get(keys[i]);
            } else {
                return false;
            }
        }
        if (currentNode instanceof JsonObject) {
            ((JsonObject) currentNode).remove(keys[keys.length - 1]);
            return true;
        }
        return false;
    }

    public void executeCommand(String commandLine) {
        String[] parts = commandLine.split(" ");
        if (parts.length == 0) return;

        String commandKey = parts[0];
        ICommand command = commandMap.get(commandKey);
        if (command == null) {
            System.out.println("Invalid command. Type 'help' for a list of commands.");
            return;
        }

        try {
            command.execute(parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[0]);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}