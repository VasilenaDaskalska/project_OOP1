package bg.tu_varna.sit.a4.fn22621660.contacts;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

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
    void print() throws  IOException;
    void help();
    void editFile(Scanner scanner) throws IOException, InvalidJsonException;
    void setFile(Scanner scanner1, Scanner scanner2) throws IOException, InvalidJsonException;
    void createFile(Scanner scanner1, Scanner scanner2) throws IOException;
    void deleteFile(Scanner scanner) throws IOException;
    void moveFileContent(Scanner scanner1, Scanner scanner2) throws IOException;
}
