package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class InsufficientFundException extends APIException {
    public InsufficientFundException(String message) {
        super(message);
    }

    public InsufficientFundException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
