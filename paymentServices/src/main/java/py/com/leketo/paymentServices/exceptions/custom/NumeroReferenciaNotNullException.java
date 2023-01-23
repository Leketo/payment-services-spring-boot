package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class NumeroReferenciaNotNullException extends APIException {
    public NumeroReferenciaNotNullException(String message) {
        super(message);
    }

    public NumeroReferenciaNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
