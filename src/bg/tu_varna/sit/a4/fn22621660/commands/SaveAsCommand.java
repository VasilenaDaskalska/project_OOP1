package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveAsCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    public SaveAsCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (jsonFileManager.getCurrentFilePath() == null) {
            System.out.println("No file is currently open.");
            return;
        }

        if (args.length < 1) {
            throw new IllegalArgumentException("File path required.");
        }

        String filePath = args[0];
        String path = args.length > 1 ? args[1] : null;
        JsonValue nodeToSave;

        if (path != null) {
            nodeToSave = jsonFileManager.getNodeAtPath(path);
            if (nodeToSave == null) {
                System.out.println("Invalid path.");
                return;
            }
        } else {
            nodeToSave = jsonFileManager.getRootNode();
        }

        Files.write(Paths.get(filePath), nodeToSave.toJsonString().getBytes());
        System.out.println("Successfully saved to " + filePath + (path != null ? " at path " + path : ""));
    }
}
