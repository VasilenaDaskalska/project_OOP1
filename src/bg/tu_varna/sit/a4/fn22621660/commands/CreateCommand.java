package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonObject;
import bg.tu_varna.sit.a4.fn22621660.json.JsonParser;
import bg.tu_varna.sit.a4.fn22621660.json.JsonPrimitive;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;
import bg.tu_varna.sit.a4.fn22621660.contacts.IJsonValidator;
import bg.tu_varna.sit.a4.fn22621660.services.JsonValidator;

/**
 * Command implementation for creating a JSON node at a specified path.
 */
public class CreateCommand implements ICommand
{
    private JsonFileManager jsonFileManager;


    /**
     * Constructs a CreateCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public CreateCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the create command.
     *
     * @param args command arguments: path and value.
     * @throws IllegalArgumentException if path or value are missing.
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

        // Extract path and value from arguments
        String path = args[0];
        StringBuilder jsonValue = new StringBuilder();

        for(int i = 1; i < args.length; i++)
        {
            jsonValue.append(args[i] + " ");
        }

        JsonValue value = new JsonPrimitive(jsonValue.toString());

        // Attempt to create the node at the specified path
        if (jsonFileManager.createNodeAtPath(path, value))
        {
            System.out.println("Successfully created value at path " + path);
        } else
        {
            System.out.println("Failed to create value at path " + path);
        }
    }
}
