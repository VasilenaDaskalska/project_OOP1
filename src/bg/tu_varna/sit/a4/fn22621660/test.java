package bg.tu_varna.sit.a4.fn22621660;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;
import bg.tu_varna.sit.a4.fn22621660.services.BaseService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        BaseService baseService = new BaseService();
        String fileName = " ";
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Map<String, Runnable> menuOptions = new HashMap<>();
        boolean fileOpened = false;

        menuOptions.put("open", () -> {
            try {
                baseService.openFile(scanner);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        menuOptions.put("delete", () -> {
            try {
                baseService.deleteFile(scanner);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        menuOptions.put("edit", () -> {
            try {
                baseService.editFile(scanner);
            } catch (IOException | InvalidJsonException e) {
                throw new RuntimeException(e);
            }
        });

        menuOptions.put("set", () -> {
            try {
                baseService.setFile(scanner, scanner1);
            } catch (IOException | InvalidJsonException e) {
                throw new RuntimeException(e);
            }
        });

        menuOptions.put("move", () -> {
            try {
                baseService.moveFileContent(scanner, scanner1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        menuOptions.put("create", () -> {
            try {
                baseService.createFile(scanner, scanner1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        menuOptions.put("save", () -> baseService.saveFile());
        menuOptions.put("save as", () -> baseService.saveAsFile(scanner));
        menuOptions.put("print", () -> {
            try {
                baseService.print(scanner);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        menuOptions.put("close", () -> baseService.closeFile());
        menuOptions.put("help", ()-> baseService.help());
        menuOptions.put("exit", () -> {
            if (fileOpened) {
                baseService.closeFile();
            }
            System.out.println("Exiting program...");
            scanner.close();
            System.exit(0);
        });

        while (true) {
            System.out.println("Options:");
            System.out.println("- open");
            System.out.println("- create");
            System.out.println("- save");
            System.out.println("- edit");
            System.out.println("- move");
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
