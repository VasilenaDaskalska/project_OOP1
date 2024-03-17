package bg.tu_varna.sit.a4.fn22621660.services;

import bg.tu_varna.sit.a4.fn22621660.contacts.IBaseService;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

import java.io.*;
import java.util.Scanner;

public class BaseService implements IBaseService
{
    private final ValidateJson validateJson = new ValidateJson();
    private  File currentFile;
    private  boolean fileOpened = false;
    private  StringBuilder content;

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
        this.fileOpened = true;
        return content.toString();
    }

    @Override
    public void openFile(Scanner scanner) throws IOException{
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        currentFile = new File(fileName);
        try {
            if(this.currentFile.exists())
            {
                String jsonContent = readFileContent(fileName);
                System.out.println("Successfully opened " + fileName + "!");
                this.validateJson.validateJSON(jsonContent);
                System.out.println("JSON is valid!");
                fileOpened = true;
            }else
            {
                if(currentFile.createNewFile())
                {
                    System.out.println("Successfully opened " + fileName + "!");
                    fileOpened = true;
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
    public void saveFile() {
        if (this.fileOpened) {
            try {
                FileWriter writer = new FileWriter(this.currentFile);
                writer.write(this.content.toString());
                writer.close();
                System.out.println("Changes successfully saved.");
            } catch (IOException e) {
                System.out.println("An error occurred while saving the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file is currently opened.");
        }
    }

    @Override
    public void saveAsFile(Scanner scanner) {
        if (this.fileOpened)
        {
            System.out.print("Enter new file path: ");
            String filePath = scanner.nextLine();
            File newFile = new File(filePath);
            try {
                FileWriter writer = new FileWriter(newFile);
                writer.write(this.content.toString());
                writer.close();
                System.out.println("Changes successfully saved to " + newFile.getName());
            } catch (IOException e) {
                System.out.println("An error occurred while saving the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file is currently opened.");
        }
    }

    @Override
    public void help()
    {
        System.out.println("The following commands are supported");
        System.out.println("open <file>\t\topens <file>");
        System.out.println("close \t \t\tcloses currently opened file");
        System.out.println("save \t \t\tsaves the currently open file");
        System.out.println("save as <file>\tsaves the currently open file in <file");
        System.out.println("help \t \t\tprints this information");
        System.out.println("exite \t \t\texists the program");
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
