package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonObject;
import bg.tu_varna.sit.a4.fn22621660.json.JsonParser;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;
import bg.tu_varna.sit.a4.fn22621660.contacts.IJsonValidator;
import bg.tu_varna.sit.a4.fn22621660.services.JsonValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OpenCommand implements ICommand
{
    private JsonFileManager jsonFileManager;
    private IJsonValidator jsonValidator;

    public OpenCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
        this.jsonValidator = new JsonValidator();
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("File path required.");
        }
        String filePath = args[0];
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found. Creating a new file with empty content.");
            if (file.createNewFile()) {
                jsonFileManager.setRootNode(new JsonObject());
            } else {
                throw new IOException("Unable to create new file.");
            }
        } else
        {
            String content = new String(Files.readAllBytes(file.toPath()));
            if(!content.isEmpty())
            {
                if (this.jsonValidator.isValidJson(content))
                {
                    jsonFileManager.setRootNode(JsonParser.parse(content));
                    jsonFileManager.setCurrentFilePath(filePath);
                    System.out.println("Successfully opened " + filePath);
                } else
                {
                    System.out.println("Failed to open " + filePath + ": Invalid JSON content.");
                }
            }else
            {
                jsonFileManager.setRootNode(JsonParser.parse(content));
                jsonFileManager.setCurrentFilePath(filePath);
                jsonFileManager.getRootNode().put(filePath, jsonFileManager.getRootNode());
                System.out.println("Successfully opened " + filePath);
            }
        }
    }
}
