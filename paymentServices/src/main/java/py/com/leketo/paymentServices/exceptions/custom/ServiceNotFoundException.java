package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class ServiceNotFoundException extends APIException {
    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
