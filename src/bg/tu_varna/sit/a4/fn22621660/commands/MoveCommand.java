package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;
import bg.tu_varna.sit.a4.fn22621660.json.JsonValue;
import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveCommand implements ICommand
{
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Source and destination file paths required.");
        }

        String sourcePath = args[0];
        String destinationPath = args[1];

        try {
            moveFile(sourcePath, destinationPath);
            System.out.println("Successfully moved file from " + sourcePath + " to " + destinationPath);
        } catch (IOException e) {
            System.out.println("Error moving file: " + e.getMessage());
        }
    }

    private void moveFile(String sourcePath, String destinationPath) throws IOException
    {
        File source =  new File(sourcePath);
        File destination = new File(destinationPath);

        if (!source.exists() || !source.isDirectory()) {
            System.out.println("Error: Source path does not exist or is not a directory.");
            return;
        }

        if (!destination.exists())
        {
            if (!destination.mkdirs())
            {
                System.out.println("Error: Could not create target directory.");
                return;
            }
        } else if (!destination.isDirectory()) {
            System.out.println("Error: Target path is not a directory.");
            return;
        }

        File[] filesToMove = source.listFiles();
        if (filesToMove != null) {
            for (File file : filesToMove) {
                Path targetPath = destination.toPath().resolve(file.getName());
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
