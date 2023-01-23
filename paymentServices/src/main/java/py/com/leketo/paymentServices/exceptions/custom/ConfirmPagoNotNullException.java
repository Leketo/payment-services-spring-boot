package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class ConfirmPagoNotNullException extends APIException {
    public ConfirmPagoNotNullException(String message) {
        super(message);
    }

    public ConfirmPagoNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
