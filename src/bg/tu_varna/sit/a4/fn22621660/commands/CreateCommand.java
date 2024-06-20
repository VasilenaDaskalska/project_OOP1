package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonObject;
import bg.tu_varna.sit.a4.fn22621660.json.JsonParser;
import bg.tu_varna.sit.a4.fn22621660.json.JsonPrimitive;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;
import bg.tu_varna.sit.a4.fn22621660.contacts.IJsonValidator;
import bg.tu_varna.sit.a4.fn22621660.services.JsonValidator;

public class CreateCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    public CreateCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) {
        if (jsonFileManager.getCurrentFilePath() == null)
        {
            System.out.println("No file is currently open.");
            return;
        }

        if (args.length < 2) {
            throw new IllegalArgumentException("Path and value required.");
        }

        String path = args[0];
        String jsonValue = args[1];
        JsonValue value = new JsonPrimitive(jsonValue);

        if (jsonFileManager.createNodeAtPath(path, value)) {
            System.out.println("Successfully created value at path " + path);
        } else {
            System.out.println("Failed to create value at path " + path);
        }
    }
}
