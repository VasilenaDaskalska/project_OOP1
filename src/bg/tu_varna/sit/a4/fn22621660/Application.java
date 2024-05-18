package bg.tu_varna.sit.a4.fn22621660;

import bg.tu_varna.sit.a4.fn22621660.model.JsonParser;

import java.io.IOException;

public class Application
{
    public static void main(String[] args) throws IOException
    {
        JsonParser parser = new JsonParser();
        parser.loadMenuOptions();
        parser.generateManu();
    }
}
