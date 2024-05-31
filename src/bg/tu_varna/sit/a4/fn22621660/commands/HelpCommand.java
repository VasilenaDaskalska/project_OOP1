package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;

public class HelpCommand implements ICommand
{

    @Override
    public void execute(String[] args) throws Exception
    {
        System.out.println("The following commands are supported:");
        System.out.println("open <file>          - Opens the specified file.");
        System.out.println("close                - Closes the currently opened file.");
        System.out.println("save [<path>]        - Saves the currently open file. Optionally, saves the JSON at the specified path.");
        System.out.println("saveas <file> [<path>] - Saves the currently open file to the specified file path. Optionally, saves the JSON at the specified path.");
        System.out.println("print                - Prints the current JSON content.");
        System.out.println("search <key>         - Searches for a key in the JSON content.");
        System.out.println("set <path> <string>  - Sets the value at the specified path to the given JSON string.");
        System.out.println("create <path> <string> - Creates a new element at the specified path with the given JSON string.");
        System.out.println("delete <path>        - Deletes the element at the specified path.");
        System.out.println("move <from> <to>     - Moves the element from the source path to the destination path.");
        System.out.println("help                 - Prints this help information.");
        System.out.println("exit                 - Exits the program.");
    }
}
