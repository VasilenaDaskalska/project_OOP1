package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

/**
 * Command implementation for searching a key in the JSON content.
 */
public class SearchCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    /**
     * Constructs a SearchCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public SearchCommand(JsonFileManager jsonFileManager)
    {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the search command to find and print the value associated with a key in the JSON content.
     *
     * @param args command arguments: key to search for.
     * @throws IllegalArgumentException if key is missing.
     * @throws Exception                if there's an issue executing the command.
     */
    @Override
    public void execute(String[] args) throws Exception
    {
        // Check if a file is currently open
        if (jsonFileManager.getCurrentFilePath() == null)
        {
            System.out.println("No file is currently open.");
            return;
        }

        // Validate command arguments
        if (args.length < 1)
        {
            throw new IllegalArgumentException("Key required.");
        }

        // Extract key from arguments
        String key = args[0];

        // Retrieve value associated with the key from JSON content
        JsonValue value = jsonFileManager.getRootNode().get(key);

        // Print the value if found, otherwise print a message
        if (value != null)
        {
            System.out.println(value.toJsonString());
        } else
        {
            System.out.println("Key not found: " + key);
        }
    }
}
