package Kate.lab6.exceptions;

import java.io.IOException;

public class ShiftImpossibleException extends IOException {
    public ShiftImpossibleException(String arg){
        super("Ошибка при добавлении элемента в очередь: " + arg);
    }
}
