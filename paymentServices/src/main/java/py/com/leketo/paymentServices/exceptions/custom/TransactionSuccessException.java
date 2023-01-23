package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class TransactionSuccessException extends APIException {
    public TransactionSuccessException(String message) {
        super(message);
    }

    public TransactionSuccessException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}