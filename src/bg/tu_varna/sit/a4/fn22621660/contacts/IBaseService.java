package bg.tu_varna.sit.a4.fn22621660.contacts;

import java.io.IOException;

public interface IBaseService
{
    String readFileContent(String fileName) throws IOException;
    void OpenFile(String fileName) throws IOException;
}
