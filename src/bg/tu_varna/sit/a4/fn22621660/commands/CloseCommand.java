package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

public class CloseCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    public CloseCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) {
        jsonFileManager.setCurrentFilePath(null);
        jsonFileManager.setRootNode(null);
        System.out.println("Successfully closed file.");
    }
}
