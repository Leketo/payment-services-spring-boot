package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class ServiceNotNullException extends APIException {
    public ServiceNotNullException(String message) {
        super(message);
    }

    public ServiceNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
