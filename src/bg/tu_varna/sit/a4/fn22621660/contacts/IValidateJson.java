package bg.tu_varna.sit.a4.fn22621660.contacts;
import java.io.IOException;

import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;
import bg.tu_varna.sit.a4.fn22621660.test;

public interface IValidateJson
{
    void validateJSON(String jsonContent) throws InvalidJsonException;
}
