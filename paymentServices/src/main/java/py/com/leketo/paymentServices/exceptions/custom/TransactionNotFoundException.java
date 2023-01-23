package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class TransactionNotFoundException extends APIException {
    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}