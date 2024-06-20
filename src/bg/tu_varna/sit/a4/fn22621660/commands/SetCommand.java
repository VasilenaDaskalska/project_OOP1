package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonPrimitive;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

/**
 * This class represents a command to set a value in a JSON file managed by JsonFileManager.
 * It implements the ICommand interface.
 */
public class SetCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    /**
     * Constructs a SetCommand with the specified JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to be used for managing JSON files.
     */
    public SetCommand(JsonFileManager jsonFileManager)
    {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the command to set a value at a specific path in the JSON file.
     * If no file is currently open, it will print an appropriate message.
     * If the path or value is not provided in the arguments, it will throw an IllegalArgumentException.
     *
     * @param args the command arguments where the first element is the path and the second element is the value.
     * @throws IllegalArgumentException if the path and value are not provided.
     */
    @Override
    public void execute(String[] args)
    {
        // Check if a file is currently open
        if (jsonFileManager.getCurrentFilePath() == null)
        {
            System.out.println("No file is currently open.");
            return;
        }

        // Validate command arguments
        if (args.length < 2)
        {
            throw new IllegalArgumentException("Path and value required.");
        }

        // Extract path and new value from arguments
        String path = args[0];

        StringBuilder jsonValue = new StringBuilder();

        for(int i = 1; i < args.length; i++)
        {
            jsonValue.append(args[i] + " ");
        }

        JsonValue value = new JsonPrimitive(jsonValue.toString());

        // Attempt to delete the node at the specified path
        if (!jsonFileManager.deleteNodeAtPath(path))
        {
            System.out.println("Failed to delete value at path " + path);
        }

        // Attempt to set the node at the specified path
        if (jsonFileManager.setNodeAtPath(path, value))
        {
            System.out.println("Successfully set value at path " + path);
        } else
        {
            System.out.println("Failed to set value at path " + path);
        }
    }
}
