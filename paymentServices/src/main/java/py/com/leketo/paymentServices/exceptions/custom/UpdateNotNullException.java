package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class UpdateNotNullException extends APIException {
    public UpdateNotNullException(String message) {
        super(message);
    }

    public UpdateNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}