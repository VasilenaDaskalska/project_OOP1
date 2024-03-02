package bg.tu_varna.sit.a4.fn22621660;
import bg.tu_varna.sit.a4.fn22621660.services.BaseService;
import java.io.IOException;
public class test {
    public static void main(String[] args) throws IOException {
        String fileName = "appsettings.Development.json";
        BaseService baseService = new BaseService();
        baseService.OpenFile(fileName);

    }
}
