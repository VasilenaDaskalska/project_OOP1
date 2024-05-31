package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    public SaveCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (jsonFileManager.getCurrentFilePath() == null) {
            System.out.println("No file is currently open.");
            return;
        }

        String path = args.length > 0 ? args[0] : null;
        if (path != null) {
            JsonValue nodeToSave = jsonFileManager.getNodeAtPath(path);
            if (nodeToSave == null) {
                System.out.println("Invalid path.");
            } else {
                Files.write(Paths.get(jsonFileManager.getCurrentFilePath()), nodeToSave.toJsonString().getBytes());
                System.out.println("Successfully saved " + jsonFileManager.getCurrentFilePath() + " at path " + path);
            }
        } else {
            Files.write(Paths.get(jsonFileManager.getCurrentFilePath()), jsonFileManager.getRootNode().toJsonString().getBytes());
            System.out.println("Successfully saved " + jsonFileManager.getCurrentFilePath());
        }
    }
}
