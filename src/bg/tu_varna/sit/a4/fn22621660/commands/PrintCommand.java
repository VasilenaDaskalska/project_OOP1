package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonFormat;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

public class PrintCommand extends JsonFormat implements ICommand
{
    private JsonFileManager jsonFileManager;

    public PrintCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (jsonFileManager.getCurrentFilePath() == null) {
            System.out.println("No file is currently open.");
        } else
        {
            JsonValue rootNode = jsonFileManager.getRootNode();
            if (rootNode != null) {
                System.out.println(formatJson(rootNode.toJsonString(), 2));
            } else {
                System.out.println("No file is currently opened.");
            }
        }
    }
}
