package bg.tu_varna.sit.a4.fn22621660.contacts;

import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

public interface IValidateJson
{
    void validateJSON(String jsonContent) throws InvalidJsonException;
}
