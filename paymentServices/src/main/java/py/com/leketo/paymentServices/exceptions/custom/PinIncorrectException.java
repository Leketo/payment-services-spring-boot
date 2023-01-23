package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class PinIncorrectException extends APIException {
    public PinIncorrectException(String message) {
        super(message);
    }

    public PinIncorrectException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}

