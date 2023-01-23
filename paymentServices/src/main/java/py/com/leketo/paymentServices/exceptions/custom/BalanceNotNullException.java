package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class BalanceNotNullException extends APIException {
    public BalanceNotNullException(String message) {
        super(message);
    }

    public BalanceNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
