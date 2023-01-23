package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class UserNotNullException extends APIException {
    public UserNotNullException(String message) {
        super(message);
    }

    public UserNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}