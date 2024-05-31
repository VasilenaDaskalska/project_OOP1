package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonParser;
import bg.tu_varna.sit.a4.fn22621660.json.JsonPrimitive;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EditCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    public EditCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (jsonFileManager.getCurrentFilePath() == null) {
            System.out.println("No file is currently open.");
            return;
        }

        if (args.length < 2) {
            throw new IllegalArgumentException("Path and new value required.");
        }

        String path = args[0];
        String newValue = args[1];
        JsonValue newValueObject = new JsonPrimitive(newValue);

        if (jsonFileManager.setNodeAtPath(path, newValueObject)) {
            System.out.println("Successfully updated value at path " + path);
        } else {
            System.out.println("Failed to update value at path " + path);
        }
    }
}

