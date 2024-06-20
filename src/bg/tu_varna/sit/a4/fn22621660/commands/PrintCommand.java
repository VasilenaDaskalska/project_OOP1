package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonFormat;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

/**
 * Command implementation for printing the JSON content.
 */
public class PrintCommand extends JsonFormat implements ICommand
{
    private JsonFileManager jsonFileManager;

    /**
     * Constructs a PrintCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public PrintCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the print command to display the current JSON content.
     *
     * @param args command arguments (not used in this command).
     * @throws Exception if there's an issue executing the command (not expected in this implementation).
     */
    @Override
    public void execute(String[] args) throws Exception
    {
        // Check if a file is currently open
        if (jsonFileManager.getCurrentFilePath() == null)
        {
            System.out.println("No file is currently open.");
        } else
        {
            // Retrieve the root node from JsonFileManager
            JsonValue rootNode = jsonFileManager.getRootNode();

            // Print the JSON content if root node exists
            if (rootNode != null)
            {
                System.out.println(formatJson(rootNode.toJsonString(), 2));
            } else
            {
                System.out.println("No file is currently opened.");
            }
        }
    }
}
