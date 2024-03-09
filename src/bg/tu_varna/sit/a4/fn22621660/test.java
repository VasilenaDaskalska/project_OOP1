package bg.tu_varna.sit.a4.fn22621660;
import bg.tu_varna.sit.a4.fn22621660.services.BaseService;
import java.io.IOException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        BaseService baseService = new BaseService();
        String fileName = " ";
        while (true) {
            System.out.println("1. Open");
            System.out.println("2. Close");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    Scanner fileNameSetter = new Scanner(System.in);
                    fileName = fileNameSetter.nextLine();
                    baseService.openFile(fileName);
                    break;
                case 2:
                    baseService.closeFile();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
