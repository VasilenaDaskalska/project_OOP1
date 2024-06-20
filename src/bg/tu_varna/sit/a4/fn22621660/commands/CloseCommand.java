package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

/**
 * Command implementation for closing the current JSON file.
 */
public class CloseCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    /**
     * Constructs a CloseCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public CloseCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    /**
     * Executes the close command.
     *
     * @param args command arguments (not used in this command).
     */
    @Override
    public void execute(String[] args) {
        jsonFileManager.setCurrentFilePath(null);
        jsonFileManager.setRootNode(null);
        System.out.println("Successfully closed file.");
    }
}
