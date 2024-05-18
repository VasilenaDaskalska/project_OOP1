package bg.tu_varna.sit.a4.fn22621660;

import bg.tu_varna.sit.a4.fn22621660.model.JsonParser;

import java.io.IOException;

public class Application
{
    /**
     * The main method of the application.
     *
     * @param args The command-line arguments passed to the program.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException
    {
        // Create an instance of JsonParser
        JsonParser parser = new JsonParser();

        // Load menu options from JSON file
        parser.loadMenuOptions();

        // Generate menu based on loaded options
        parser.generateManu();
    }
}
