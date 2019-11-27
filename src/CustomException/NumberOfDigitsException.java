package CustomException;

/**
 * @author Vyacheslav Alekseevich
 * @date 27/11/2019
 */

public class NumberOfDigitsException extends Exception{
    public NumberOfDigitsException(String message) {
        super(message);
    }
}
