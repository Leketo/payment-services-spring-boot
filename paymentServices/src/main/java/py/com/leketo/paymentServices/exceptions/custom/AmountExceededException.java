package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class AmountExceededException extends APIException {
    public AmountExceededException(String message) {
        super(message);
    }

    public AmountExceededException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
