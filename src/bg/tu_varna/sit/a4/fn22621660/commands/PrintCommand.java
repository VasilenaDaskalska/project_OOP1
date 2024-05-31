package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

public class PrintCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    public PrintCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) {
        if (jsonFileManager.getCurrentFilePath() == null) {
            System.out.println("No file is currently open.");
        } else
        {
            if(jsonFileManager.getRootNode() != null)
            {
                System.out.println(jsonFileManager.getRootNode().toJsonString());
            }else
            {
                System.out.println("File is empty!");
            }
        }
    }
}
