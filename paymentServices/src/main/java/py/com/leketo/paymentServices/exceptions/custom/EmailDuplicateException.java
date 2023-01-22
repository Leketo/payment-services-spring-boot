package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class EmailDuplicateException extends APIException {
    public EmailDuplicateException(String message) {
        super(message);
    }

    public EmailDuplicateException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
