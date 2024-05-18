package bg.tu_varna.sit.a4.fn22621660.services;

import bg.tu_varna.sit.a4.fn22621660.contacts.IBaseService;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        System.out.print("Enter path: ");
        String path = scanner.nextLine();
        currentFile = new File(path);

        try {
            Paths.get(path);
        } catch (InvalidPathException e) {
            System.out.println("Invalid file path!");
            return;
        }

        File newFile = new File(path);

        if(newFile.isDirectory())
        {
            System.out.println("This is a directory not a file!");
            return;
        }

        try {
            if(this.currentFile.exists())
            {
                String jsonContent = readFileContent(path);
                System.out.println("Successfully opened " + newFile.getName() + "!");
                this.validateJson.validateJSON(jsonContent);
                System.out.println("JSON is valid!");
                fileOpened = true;
            }else
            {
                if(currentFile.createNewFile())
                {
                    System.out.println("Successfully opened " + path + "!");
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
    public void deleteFile(Scanner scanner) throws IOException{
        System.out.print("Enter path: ");
        String path = scanner.nextLine();

        try {
            Paths.get(path);
        } catch (InvalidPathException e) {
            System.out.println("Invalid file path!");
            return;
        }

        currentFile = new File(path);

        if(currentFile.isDirectory())
        {
            System.out.println("This is a directory not a file!");
            return;
        }

        if (!this.currentFile.exists())
        {
            System.out.println("File doesn't exit!");
        }else
        {
            if (currentFile.delete())
            {
                System.out.println("Successfully deleted " + currentFile.getName() + "!");
                fileOpened = false;
            } else {
                System.out.println("Failed to delete the file!");
            }
        }
    }

    @Override
    public void createFile(Scanner scanner1, Scanner scanner2) throws IOException{
        System.out.print("Enter path: ");
        String path = scanner1.nextLine();

        try {
            Paths.get(path);
        } catch (InvalidPathException e) {
            System.out.println("Invalid file path!");
            return;
        }

        currentFile = new File(path);

        if(currentFile.isDirectory())
        {
            System.out.println("This is a directory not a file!");
            return;
        }

        try {
            if(!this.currentFile.exists())
            {
                System.out.println("Enter content (press Enter twice to finish):");
                scanner2.useDelimiter("\n\n");
                String newContent = scanner2.next();
                try
                {
                    this.validateJson.validateJSON(newContent);
                }catch (InvalidJsonException ex)
                {
                    System.err.println("Invalid JSON: " + ex.getMessage());
                    return;
                }
                if(currentFile.createNewFile())
                {
                    FileWriter writer = new FileWriter(this.currentFile);
                    writer.write(newContent);
                    writer.close();
                    System.out.println("New file is created.");
                }else
                {
                    System.out.println("Failed to created the file!");
                }
            }else
            {
                System.out.println("File already exit!");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while accessing the file! " + e.getMessage());
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

            try {
                Paths.get(filePath);
            } catch (InvalidPathException e) {
                System.out.println("Invalid file path!");
                return;
            }

            File newFile = new File(filePath);

            if(newFile.isDirectory())
            {
                filePath = filePath + "\\" + this.currentFile;
                newFile = new File(filePath);
            }

            try {
                FileWriter writer = new FileWriter(newFile);
                writer.write(this.content.toString());
                writer.close();
                System.out.println("Changes successfully saved " + newFile.getName());
            } catch (IOException e) {
                System.out.println("An error occurred while saving the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file is currently opened.");
        }
    }

    @Override
    public void print() throws IOException
    {
        if(fileOpened)
        {
            try {
                Scanner fileScanner = new Scanner(currentFile);
                this.content = new StringBuilder();
                this.content.setLength(0);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    this.content.append(line).append("\n");
                }
                fileScanner.close();
                this.fileOpened = true;
                System.out.println(content.toString());
            } catch (IOException ex) {
                System.out.println("An error occurred while reading the file.");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void help()
    {
        System.out.println("The following commands are supported");
        System.out.println("open <path>            opens <path>");
        System.out.println("create <path> <string> creates file with <path> and saves content in it <string>");
        System.out.println("edit                   edit currently opened file");
        System.out.println("move <from> <to>       move form <path> to <path>");
        System.out.println("save                   saves the currently open file");
        System.out.println("save as <path>         saves the currently open file in <path>");
        System.out.println("set <path> <string>    replacing the content in <file> with <string>");
        System.out.println("print                  prints everything in current <file>");
        System.out.println("delete <path>          delete file on <path>");
        System.out.println("close                  closes currently opened file");
        System.out.println("help                   prints this information");
        System.out.println("exit                   exists the program");
    }

    @Override
    public void editFile(Scanner scanner) throws IOException, InvalidJsonException
    {
        if (fileOpened)
        {
            System.out.println("Enter content (press Enter twice to finish):");
            scanner.useDelimiter("\n\n");
            String newContent = scanner.next();
            this.validateJson.validateJSON(newContent);
            content.append(newContent);
            System.out.println("File content updated.");
            System.out.println("If you want to save changes in the file, please choose {save} option!");
        } else {
            System.out.println("No file is currently opened.");
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

        @Override
        public void setFile(Scanner scanner1, Scanner scanner2) throws IOException, InvalidJsonException
        {
            System.out.print("Enter path: ");
            String path = scanner1.nextLine();

            try {
                Paths.get(path);
            } catch (InvalidPathException e) {
                System.out.println("Invalid file path!");
                return;
            }

            currentFile = new File(path);

            if(currentFile.isDirectory())
            {
                System.out.println("This is a directory not a file!");
                return;
            }

            if(this.currentFile.exists())
            {
                try
                {
                    String jsonContent = readFileContent(path);
                    fileOpened = true;
                }catch (IOException ex)
                {
                    System.out.println("Failed to open file!");
                    ex.printStackTrace();
                }

                if(fileOpened)
                {
                    System.out.println("Enter new content (press Enter twice to finish):");
                    scanner2.useDelimiter("\n\n");
                    String newContent = scanner2.next();
                    content.setLength(0);
                    this.validateJson.validateJSON(newContent);
                    content.append(newContent);
                    System.out.println("File content updated.");
                    System.out.println("If you want to save changes in the file, please choose {save} option!");
                }
            }
        }

    @Override
    public void moveFileContent(Scanner scanner1, Scanner scanner2) throws IOException
    {
        System.out.print("Enter path from which you want to move content: ");
        String fromPath = scanner1.nextLine();
        System.out.print("Enter path to which you want to move content: ");
        String toPath = scanner2.nextLine();
        File sourceDir = new File(fromPath);
        File targetDir = new File(toPath);

        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            System.out.println("Error: Source path does not exist or is not a directory.");
            return;
        }

        if (!targetDir.exists()) {
            if (!targetDir.mkdirs()) {
                System.out.println("Error: Could not create target directory.");
                return;
            }
        } else if (!targetDir.isDirectory()) {
            System.out.println("Error: Target path is not a directory.");
            return;
        }

        File[] filesToMove = sourceDir.listFiles();
        if (filesToMove != null) {
            for (File file : filesToMove) {
                Path targetPath = targetDir.toPath().resolve(file.getName());
                try {
                    Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Moved: " + file.getName());
                } catch (IOException e) {
                    System.out.println("Error: Failed to move file " + file.getName());
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error: Failed to list files in the source directory.");
        }
    }
}
