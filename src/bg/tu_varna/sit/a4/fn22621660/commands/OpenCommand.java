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
import java.nio.file.Paths;

public class OpenCommand implements ICommand
{
    private JsonFileManager jsonFileManager;
    private IJsonValidator jsonValidator;

    public OpenCommand(JsonFileManager jsonFileManager) {
        this.jsonFileManager = jsonFileManager;
        this.jsonValidator = new JsonValidator();
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("File path required.");
        }

        String filePath = args[0];
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath))).trim();
            JsonObject rootNode;

            if (content.isEmpty()) 
            {
                rootNode = new JsonObject();
            } else {
                if (!this.jsonValidator.isValidJson(content))
                {
                    return;
                }
                rootNode = parseJson(content);
            }

            jsonFileManager.setRootNode(rootNode);
            jsonFileManager.setCurrentFilePath(filePath);
            System.out.println("Successfully opened " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
    }

    private JsonObject parseJson(String content) throws Exception
    {
        JsonParser parser = new JsonParser();
        return parser.parseObject(content);
    }
}
