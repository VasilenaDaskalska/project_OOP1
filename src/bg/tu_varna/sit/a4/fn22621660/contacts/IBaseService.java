package bg.tu_varna.sit.a4.fn22621660.contacts;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public interface IBaseService
{
    String readFileContent(String fileName) throws IOException;
    void openFile(Scanner scanner) throws IOException;
    void closeFile();
    void saveFile();
    void saveAsFile(Scanner scanner);
    void print(Scanner scanner) throws  IOException;
    void help();
}
