package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class RegisterPagoNotNullException extends APIException {
    public RegisterPagoNotNullException(String message) {
        super(message);
    }

    public RegisterPagoNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
