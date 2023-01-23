package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class DeudaNotFoundException extends APIException {
    public DeudaNotFoundException(String message) {
        super(message);
    }

    public DeudaNotFoundException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
