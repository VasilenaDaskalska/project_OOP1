package bg.tu_varna.sit.a4.fn22621660.contacts;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface defining the basic operations for file management.
 */
public interface IBaseService
{
    /**
     * Reads the content of a file given its name.
     *
     * @param fileName the name of the file to read.
     * @return the content of the file as a String.
     * @throws IOException if an I/O error occurs.
     */
    String readFileContent(String fileName) throws IOException;

    /**
     * Opens a file for reading and editing.
     *
     * @param scanner the scanner to read user input.
     * @throws IOException if an I/O error occurs.
     */
    void openFile(Scanner scanner) throws IOException;

    /**
     * Closes the currently opened file.
     */
    void closeFile();

    /**
     * Saves the currently opened file.
     */
    void saveFile();

    /**
     * Saves the currently opened file to a specified location.
     *
     * @param scanner the scanner to read user input for the file path.
     */
    void saveAsFile(Scanner scanner);

    /**
     * Prints the content of the currently opened file in a readable format.
     *
     * @throws IOException if an I/O error occurs.
     */
    void print() throws  IOException;

    /**
     * Displays help information about the available commands.
     */
    void help();

    /**
     * Edits the content of the currently opened file.
     *
     * @param scanner the scanner to read user input.
     * @throws IOException if an I/O error occurs.
     * @throws InvalidJsonException if the provided JSON is invalid.
     */
    void editFile(Scanner scanner) throws IOException, InvalidJsonException;

    /**
     * Sets the content of an element specified by a path in the currently opened file.
     *
     * @param scanner1 the scanner to read user input for the path.
     * @param scanner2 the scanner to read user input for the new content.
     * @throws IOException if an I/O error occurs.
     * @throws InvalidJsonException if the provided JSON is invalid.
     */
    void setFile(Scanner scanner1, Scanner scanner2) throws IOException, InvalidJsonException;

    /**
     * Creates a new file at the specified location.
     *
     * @param scanner1 the scanner to read user input for the file path.
     * @param scanner2 the scanner to read user input for the initial content.
     * @throws IOException if an I/O error occurs.
     */
    void createFile(Scanner scanner1, Scanner scanner2) throws IOException;

    /**
     * Deletes the file at the specified location.
     *
     * @param scanner the scanner to read user input for the file path.
     * @throws IOException if an I/O error occurs.
     */
    void deleteFile(Scanner scanner) throws IOException;

    /**
     * Moves the content of one file to another location.
     *
     * @param scanner1 the scanner to read user input for the source path.
     * @param scanner2 the scanner to read user input for the target path.
     * @throws IOException if an I/O error occurs.
     */
    void moveFileContent(Scanner scanner1, Scanner scanner2) throws IOException;
}
