package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonParser;
import bg.tu_varna.sit.a4.fn22621660.json.JsonPrimitive;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Command implementation for editing (updating) a JSON node at a specified path.
 */
public class EditCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    /**
     * Constructs an EditCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public EditCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the edit command.
     *
     * @param args command arguments: path and new value.
     * @throws IllegalArgumentException if path or new value are missing.
     */
    @Override
    public void execute(String[] args) throws IOException
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
            throw new IllegalArgumentException("Path and new value required.");
        }

        // Extract path and new value from arguments
        String path = args[0];

        StringBuilder newValue = new StringBuilder();

        for(int i = 1; i < args.length; i++)
        {
            newValue.append(args[i] + " ");
        }

        JsonValue newValueObject = new JsonPrimitive(newValue.toString());

        // Attempt to set (edit) the node at the specified path with the new value
        if (jsonFileManager.setNodeAtPath(path, newValueObject))
        {
            System.out.println("Successfully updated value at path " + path);
        } else
        {
            System.out.println("Failed to update value at path " + path);
        }
    }
}

