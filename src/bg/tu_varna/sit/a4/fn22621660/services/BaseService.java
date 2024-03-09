package bg.tu_varna.sit.a4.fn22621660.services;

import bg.tu_varna.sit.a4.fn22621660.contacts.IBaseService;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

import java.io.*;
import java.util.Scanner;

public class BaseService implements IBaseService
{
    private final ValidateJson validateJson = new ValidateJson();
    private File currentFile;
    private boolean fileOpened;
    private StringBuilder content;

    @Override
    public String readFileContent(String fileName) throws IOException {
        this.currentFile = new File(fileName);
        Scanner fileScanner = new Scanner(currentFile);
        this.content = new StringBuilder();
        this.content.setLength(0);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            this.content.append(line).append("\n");
        }
        fileScanner.close();
        fileOpened = true;
        return content.toString();
    }

    @Override
    public void openFile(String fileName) throws IOException{
        this.currentFile = new File(fileName);
        try {
            if(this.currentFile.exists())
            {
                String jsonContent = readFileContent(fileName);
                System.out.println("Successfully opened " + fileName + "!");
                this.validateJson.validateJSON(jsonContent);
                System.out.println("JSON is valid!");
                this.fileOpened = true;
            }else
            {
                if(currentFile.createNewFile())
                {
                    System.out.println("Successfully opened " + fileName + "!");
                    this.fileOpened = true;
                }else
                {
                    System.out.println("Failed to created the file!");
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred while accessing the file! " + e.getMessage());
        } catch (InvalidJsonException e) {
            System.err.println("Invalid JSON: " + e.getMessage());
        }
    }

    @Override
    public void closeFile()
    {
        if (this.fileOpened) {
            try {
                FileWriter writer = new FileWriter(this.currentFile);
                writer.write(content.toString());
                writer.close();
                System.out.println("File closed successfully.");
                this.fileOpened = false;
                this.content.setLength(0);
            } catch (IOException e) {
                System.out.println("An error occurred while closing the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file is currently opened.");
        }
    }
}
