package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

public class SearchCommand implements ICommand
{
    private JsonFileManager jsonFileManager;

    public SearchCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) {
        if (jsonFileManager.getCurrentFilePath() == null) {
            System.out.println("No file is currently open.");
            return;
        }

        if (args.length < 1) {
            throw new IllegalArgumentException("Key required.");
        }

        String key = args[0];
        JsonValue value = jsonFileManager.getRootNode().get(key);
        if (value != null) {
            System.out.println(value.toJsonString());
        } else {
            System.out.println("Key not found: " + key);
        }
    }
}
