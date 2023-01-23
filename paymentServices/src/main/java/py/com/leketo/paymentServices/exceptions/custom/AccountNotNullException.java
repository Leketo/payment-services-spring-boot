package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class AccountNotNullException extends APIException {
    public AccountNotNullException(String message) {
        super(message);
    }

    public AccountNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
