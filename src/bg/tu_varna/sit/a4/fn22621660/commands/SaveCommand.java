package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonFormat;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Command implementation for saving the JSON content to the currently opened file path.
 */
public class SaveCommand extends JsonFormat implements ICommand
{
    private JsonFileManager jsonFileManager;

    /**
     * Constructs a SaveCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public SaveCommand(JsonFileManager jsonFileManager)
    {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the save command to save the JSON content to the currently opened file path.
     *
     * @param args command arguments (not used in this command).
     * @throws Exception if there's an issue executing the command (e.g., JSON formatting).
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

        try {
            // Format JSON content
            String jsonString = formatJson(jsonFileManager.getRootNode().toJsonString(), 2);

            // Write JSON content to the currently opened file path
            Files.write(Paths.get(jsonFileManager.getCurrentFilePath()), jsonString.getBytes());

            System.out.println("Successfully saved " + jsonFileManager.getCurrentFilePath());
        }catch (IOException e)
        {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
