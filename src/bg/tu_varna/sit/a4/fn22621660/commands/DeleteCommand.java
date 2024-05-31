package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

public class DeleteCommand  implements ICommand
{
    private JsonFileManager jsonFileManager;

    public DeleteCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) {
        if (jsonFileManager.getCurrentFilePath() == null) {
            System.out.println("No file is currently open.");
            return;
        }

        if (args.length < 1) {
            throw new IllegalArgumentException("Path required.");
        }

        String path = args[0];
        if (jsonFileManager.deleteNodeAtPath(path)) {
            System.out.println("Successfully deleted value at path " + path);
        } else {
            System.out.println("Failed to delete value at path " + path);
        }
    }
}
