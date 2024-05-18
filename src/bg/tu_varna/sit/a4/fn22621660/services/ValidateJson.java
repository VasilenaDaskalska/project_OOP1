package bg.tu_varna.sit.a4.fn22621660.services;

import bg.tu_varna.sit.a4.fn22621660.contacts.IValidateJson;
import bg.tu_varna.sit.a4.fn22621660.exeptions.InvalidJsonException;

public class ValidateJson implements IValidateJson
{
    @Override
    public void validateJSON(String jsonContent) throws InvalidJsonException {
        int curlyBraces = 0;
        int squareBrackets = 0;
        boolean inQuotes = false;

        for (int i = 0; i < jsonContent.length(); i++) {
            char c = jsonContent.charAt(i);

            if (c == '{' && !inQuotes) {
                curlyBraces++;
            } else if (c == '}' && !inQuotes) {
                curlyBraces--;
                if (curlyBraces < 0) {
                    throw new InvalidJsonException("Грешка: Неправилно затваряне на фигурни скоби.");
                }
            } else if (c == '[' && !inQuotes) {
                squareBrackets++;
            } else if (c == ']' && !inQuotes) {
                squareBrackets--;
                if (squareBrackets < 0) {
                    throw new InvalidJsonException("Грешка: Неправилно затваряне на квадратни скоби.");
                }
            }
            else if (c == '"') {
                inQuotes = !inQuotes;
            }
        }
    }
}
