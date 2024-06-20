package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonFormat;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveCommand extends JsonFormat implements ICommand
{
    private JsonFileManager jsonFileManager;

    public SaveCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (jsonFileManager.getCurrentFilePath() == null) {
            System.out.println("No file is currently open.");
            return;
        }
        String jsonString = formatJson(jsonFileManager.getRootNode().toJsonString(), 2);
        Files.write(Paths.get(jsonFileManager.getCurrentFilePath()), jsonString.getBytes());
        System.out.println("Successfully saved " + jsonFileManager.getCurrentFilePath());
    }
}
