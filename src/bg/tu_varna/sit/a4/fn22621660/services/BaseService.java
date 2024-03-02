package bg.tu_varna.sit.a4.fn22621660.services;

import bg.tu_varna.sit.a4.fn22621660.contacts.IBaseService;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BaseService implements IBaseService
{
    ValidateJson validateJson = new ValidateJson();
    @Override
    public String readFileContent(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    @Override
    public void openFile(String fileName) throws IOException {
        try {
            String jsonContent = readFileContent(fileName);
            validateJson.validateJSON(jsonContent);
            System.out.println("JSON е валиден.");
        } catch (IOException e) {
            System.err.println("Грешка при четене на файла: " + e.getMessage());
        } catch (InvalidJsonException e) {
            System.err.println("Невалиден JSON: " + e.getMessage());
        }
    }
}
