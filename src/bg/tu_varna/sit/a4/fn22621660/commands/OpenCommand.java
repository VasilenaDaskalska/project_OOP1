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

/**
 * Command implementation for opening and parsing a JSON file.
 */
public class OpenCommand implements ICommand
{
    private JsonFileManager jsonFileManager;
    private IJsonValidator jsonValidator;

    /**
     * Constructs an OpenCommand with the given JsonFileManager.
     *
     * @param jsonFileManager the JsonFileManager instance to operate on.
     */
    public OpenCommand(JsonFileManager jsonFileManager)
    {
        this.jsonFileManager = jsonFileManager;
        this.jsonValidator = new JsonValidator();
    }

    /**
     * Executes the open command to read and parse a JSON file.
     *
     * @param args command arguments: file path.
     * @throws IllegalArgumentException if file path is missing.
     */
    @Override
    public void execute(String[] args) throws IOException {
        // Validate command arguments
        if (args.length < 1) {
            throw new IllegalArgumentException("File path required.");
        }

        // Extract file path from arguments
        String filePath = args[0];

        File file = new File(filePath);

        //Check if file exists
        if(file.exists())
        {
            try {
                // Read file content
                String content = new String(Files.readAllBytes(Paths.get(filePath))).trim();
                JsonObject rootNode;

                // Parse JSON content
                if (content.isEmpty()) {
                    rootNode = new JsonObject();
                } else {
                    // Validate JSON content
                    if (!this.jsonValidator.isValidJson(content)) {
                        return;
                    }
                    rootNode = parseJson(content);
                }

                // Set root node and current file path in JsonFileManager
                jsonFileManager.setRootNode(rootNode);
                jsonFileManager.setCurrentFilePath(filePath);

                System.out.println("Successfully opened " + filePath);

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error parsing JSON: " + e.getMessage());
            }
        }else
        {
            try {

                //Creating new file
                if(file.createNewFile())
                {
                    System.out.println("Successfully created " + filePath);
                    JsonObject rootNode = new JsonObject();
                    jsonFileManager.setRootNode(rootNode);
                    jsonFileManager.setCurrentFilePath(filePath);
                }
            }catch (Exception e)
            {
                System.out.println("Error creating file: " + e.getMessage());
            }

        }
    }

    private JsonObject parseJson(String content) throws Exception
    {
        JsonParser parser = new JsonParser();
        return parser.parseObject(content);
    }
}
