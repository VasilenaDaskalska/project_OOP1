package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonFormat;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Command implementation for saving the JSON content to a specified file path.
 */
public class SaveAsCommand extends JsonFormat implements ICommand
{
    private JsonFileManager jsonFileManager;

    /**
     * Constructs a SaveAsCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public SaveAsCommand(JsonFileManager jsonFileManager)
    {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the save-as command to save the JSON content to a specified file path.
     *
     * @param args command arguments: file path.
     * @throws IllegalArgumentException if file path is missing.
     * @throws Exception                if there's an issue executing the command (e.g., JSON formatting).
     */
    @Override
    public void execute(String[] args) throws Exception
    {
        // Validate command arguments
        if (args.length < 1)
        {
            throw new IllegalArgumentException("File path required.");
        }

        // Extract file path from arguments
        String filePath = args[0];

        try {
            // Format JSON content
            String jsonString = formatJson(jsonFileManager.getRootNode().toJsonString(), 2);

            // Write JSON content to file
            Files.write(Paths.get(filePath), jsonString.getBytes());

            System.out.println("Successfully saved as " + filePath);
        } catch (IOException e)
        {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
