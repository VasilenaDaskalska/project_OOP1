package bg.tu_varna.sit.a4.fn22621660;

import bg.tu_varna.sit.a4.fn22621660.model.JsonFileManager;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        JsonFileManager jsonFileManager = new JsonFileManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String commandLine = scanner.nextLine();
            jsonFileManager.executeCommand(commandLine);
        }
    }
}
