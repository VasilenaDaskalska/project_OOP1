package bg.tu_varna.sit.a4.fn22621660.model;

import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;
import bg.tu_varna.sit.a4.fn22621660.services.BaseService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class responsible for parsing JSON and handling menu operations.
 */
public class JsonParser
{
    private BaseService baseService;
    private Map<String, Runnable> menuOptions;
    private boolean fileOpened;

    /**
     * Constructs a new JsonParser.
     */
    public JsonParser()
    {
        this.baseService = new BaseService();
        this.menuOptions = new HashMap<>();
        this.fileOpened = false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        JsonParser that = (JsonParser) o;
        return fileOpened == that.fileOpened && Objects.equals(baseService, that.baseService) && Objects.equals(menuOptions, that.menuOptions);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(baseService, menuOptions, fileOpened);
    }

    /**
     * Loads the menu options and their corresponding actions.
     */
    public void loadMenuOptions()
    {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        this.menuOptions.put("open", () ->
        {
            try
            {
                this.baseService.openFile(scanner);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        this.menuOptions.put("delete", () ->
        {
            try
            {
                this.baseService.deleteFile(scanner);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        this.menuOptions.put("edit", () ->
        {
            try
            {
                this.baseService.editFile(scanner);
            } catch (IOException | InvalidJsonException e) {
                throw new RuntimeException(e);
            }
        });

        this.menuOptions.put("set", () ->
        {
            try
            {
                this.baseService.setFile(scanner, scanner1);
            } catch (IOException | InvalidJsonException e) {
                throw new RuntimeException(e);
            }
        });

        this.menuOptions.put("move", () ->
        {
            try
            {
                this.baseService.moveFileContent(scanner, scanner1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        this.menuOptions.put("create", () ->
        {
            try
            {
                this.baseService.createFile(scanner, scanner1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        this.menuOptions.put("save", () -> this.baseService.saveFile());

        this.menuOptions.put("save as", () -> this.baseService.saveAsFile(scanner));

        this.menuOptions.put("print", () ->
        {
            try
            {
                this.baseService.print();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        this.menuOptions.put("close", () -> this.baseService.closeFile());

        this.menuOptions.put("help", ()-> this.baseService.help());

        this.menuOptions.put("exit", () ->
        {
            if (fileOpened)
            {
                this.baseService.closeFile();
            }
            System.out.println("Exiting program...");
            scanner.close();
            System.exit(0);
        });
    }

    /**
     * Generates and displays the menu, and handles user input.
     */
    public void generateManu()
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Options:");
            System.out.println("- open");
            System.out.println("- create");
            System.out.println("- edit");
            System.out.println("- move");
            System.out.println("- save");
            System.out.println("- save as");
            System.out.println("- print");
            System.out.println("- set");
            System.out.println("- delete");
            System.out.println("- close");
            System.out.println("- help");
            System.out.println("- exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toLowerCase();

            Runnable action = menuOptions.get(choice);
            if (action != null) {
                action.run();
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
