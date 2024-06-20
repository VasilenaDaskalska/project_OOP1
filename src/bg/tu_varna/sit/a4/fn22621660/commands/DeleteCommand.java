package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

/**
 * Command implementation for deleting a JSON node at a specified path.
 */
public class DeleteCommand  implements ICommand
{
    private JsonFileManager jsonFileManager;

    /**
     * Constructs a DeleteCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public DeleteCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the delete command.
     *
     * @param args command arguments: path.
     * @throws IllegalArgumentException if path is missing.
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
        if (args.length < 1)
        {
            throw new IllegalArgumentException("Path required.");
        }

        // Extract path from arguments
        String path = args[0];

        // Attempt to delete the node at the specified path
        if (jsonFileManager.deleteNodeAtPath(path))
        {
            System.out.println("Successfully deleted value at path " + path);
        } else
        {
            System.out.println("Failed to delete value at path " + path);
        }
    }
}
