package bg.tu_varna.sit.a4.fn22621660;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;
import bg.tu_varna.sit.a4.fn22621660.model.JsonParser;
import bg.tu_varna.sit.a4.fn22621660.services.BaseService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException
    {
        JsonParser parser = new JsonParser();
        parser.loadMenuOptions();
        parser.generateManu();
    }
}
