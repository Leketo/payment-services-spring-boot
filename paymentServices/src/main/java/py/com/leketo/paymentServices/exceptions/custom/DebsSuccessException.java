package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class DebsSuccessException extends APIException {
    public DebsSuccessException(String message) {
        super(message);
    }

    public DebsSuccessException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}