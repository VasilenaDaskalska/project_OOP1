package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonFormat;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveAsCommand extends JsonFormat implements ICommand
{
    private JsonFileManager jsonFileManager;

    public SaveAsCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
    }

    @Override
    public void execute(String[] args) throws Exception
    {
        if (args.length < 1) {
            throw new IllegalArgumentException("File path required.");
        }

        String filePath = args[0];

        try {
            String jsonString = formatJson(jsonFileManager.getRootNode().toJsonString(), 2);
            Files.write(Paths.get(filePath), jsonString.getBytes());
            System.out.println("Successfully saved as " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
