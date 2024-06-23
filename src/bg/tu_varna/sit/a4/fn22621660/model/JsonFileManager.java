package bg.tu_varna.sit.a4.fn22621660.model;

import bg.tu_varna.sit.a4.fn22621660.commands.*;
import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonObject;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages a JSON file system with commands to manipulate JSON data.
 */
public class JsonFileManager {
    private JsonObject rootNode;
    private String currentFilePath;
    private Map<String, ICommand> commandMap;

    /**
     * Initializes the JsonFileManager with commands for file management.
     */
    public JsonFileManager() {
        commandMap = new HashMap<>();
        // Initialize commands with references to this JsonFileManager instance
        commandMap.put("open", new OpenCommand(this));
        commandMap.put("close", new CloseCommand(this));
        commandMap.put("save", new SaveCommand(this));
        commandMap.put("save_as", new SaveAsCommand(this));
        commandMap.put("print", new PrintCommand(this));
        commandMap.put("search", new SearchCommand(this));
        commandMap.put("set", new SetCommand(this));
        commandMap.put("create", new CreateCommand(this));
        commandMap.put("delete", new DeleteCommand(this));
        commandMap.put("move", new MoveCommand());
        commandMap.put("edit", new EditCommand(this));
        commandMap.put("exit", new ExitCommand());
        commandMap.put("help", new HelpCommand());
    }

    /**
     * Sets the root JSON object for the file manager.
     *
     * @param rootNode the root JSON object to set.
     */
    public void setRootNode(JsonObject rootNode) {
        this.rootNode = rootNode;
    }

    /**
     * Retrieves the root JSON object of the file manager.
     *
     * @return the root JSON object.
     */
    public JsonObject getRootNode() {
        return rootNode;
    }

    /**
     * Sets the current file path being managed.
     *
     * @param currentFilePath the current file path to set.
     */
    public void setCurrentFilePath(String currentFilePath) {
        this.currentFilePath = currentFilePath;
    }

    /**
     * Retrieves the current file path being managed.
     *
     * @return the current file path.
     */
    public String getCurrentFilePath() {
        return currentFilePath;
    }

    /**
     * Retrieves the JSON value at the specified path.
     *
     * @param path the path to the JSON value, e.g., "root.property.subproperty".
     * @return the JSON value at the specified path, or null if not found.
     */
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

    /**
     * Sets the JSON value at the specified path.
     *
     * @param path  the path to the JSON value, e.g., "root.property.subproperty".
     * @param value the JSON value to set at the path.
     * @return true if the value was successfully set, false otherwise.
     */
    public boolean setNodeAtPath(String path, JsonValue value) {
        String[] keys = path.split("\\.");

        if(rootNode == null)
        {
            rootNode = new JsonObject();
        }

        JsonValue currentNode = rootNode;

        for (int i = 0; i < keys.length - 1; i++) {
            if (currentNode instanceof JsonObject) {
                currentNode = (JsonValue) ((JsonObject) currentNode).get(keys[i]);
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

    /**
     * Creates a JSON object at the specified path if it does not already exist.
     *
     * @param path  the path to the JSON object, e.g., "root.property.subproperty".
     * @param value the initial JSON value to set at the end of the path.
     * @return true if the JSON object was successfully created, false otherwise.
     */
    public boolean createNodeAtPath(String path, JsonValue value) {
        String[] keys = path.split("\\.");
        JsonValue currentNode = rootNode;
        for (int i = 0; i < keys.length - 1; i++) {
            if (currentNode instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) currentNode;
                if (jsonObject.get(keys[i]) == null) {
                    jsonObject.put(keys[i], new JsonObject());
                }
                currentNode = (JsonValue) jsonObject.get(keys[i]);
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

    /**
     * Deletes the JSON value at the specified path.
     *
     * @param path the path to the JSON value to delete, e.g., "root.property.subproperty".
     * @return true if the value was successfully deleted, false otherwise.
     */
    public boolean deleteNodeAtPath(String path) {
        String[] keys = path.split("\\.");
        JsonValue currentNode = rootNode;
        for (int i = 0; i < keys.length - 1; i++) {
            if (currentNode instanceof JsonObject) {
                currentNode = (JsonValue) ((JsonObject) currentNode).get(keys[i]);
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

    /**
     * Executes a command specified by the command line.
     *
     * @param commandLine the command line containing the command and optional arguments.
     */
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