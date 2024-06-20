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

/**
 * Command implementation for moving files from a source directory to a destination directory.
 */
public class MoveCommand implements ICommand
{
    /**
     * Executes the move command to move files from a source directory to a destination directory.
     *
     * @param args command arguments: source path and destination path.
     * @throws IllegalArgumentException if source or destination paths are missing.
     */
    @Override
    public void execute(String[] args)
    {
        // Validate command arguments
        if (args.length < 2)
        {
            throw new IllegalArgumentException("Source and destination file paths required.");
        }

        // Extract source and destination paths from arguments
        String sourcePath = args[0];
        String destinationPath = args[1];

        try {
            moveFile(sourcePath, destinationPath);
            System.out.println("Successfully moved file from " + sourcePath + " to " + destinationPath);
        } catch (IOException e)
        {
            System.out.println("Error moving file: " + e.getMessage());
        }
    }

    /**
     * Moves files from the source directory to the destination directory.
     *
     * @param sourcePath      path to the source directory.
     * @param destinationPath path to the destination directory.
     * @throws IOException if an I/O error occurs during file operations.
     */
    private void moveFile(String sourcePath, String destinationPath) throws IOException
    {
        File source =  new File(sourcePath);
        File destination = new File(destinationPath);

        // Validate source directory
        if (!source.exists() || !source.isDirectory())
        {
            System.out.println("Error: Source path does not exist or is not a directory.");
            return;
        }

        // Validate destination directory
        if (!destination.exists())
        {
            if (!destination.mkdirs())
            {
                System.out.println("Error: Could not create target directory.");
                return;
            }
        } else if (!destination.isDirectory())
        {
            System.out.println("Error: Target path is not a directory.");
            return;
        }

        // Get list of files to move
        File[] filesToMove = source.listFiles();

        // Move each file to the destination directory
        if (filesToMove != null)
        {
            for (File file : filesToMove)
            {
                Path targetPath = destination.toPath().resolve(file.getName());
                try {
                    Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Moved: " + file.getName());
                } catch (IOException e)
                {
                    System.out.println("Error: Failed to move file " + file.getName());
                    e.printStackTrace();
                }
            }
        } else
        {
            System.out.println("Error: Failed to list files in the source directory.");
        }
    }
}
