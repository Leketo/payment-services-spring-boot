package py.com.leketo.paymentServices.exceptions.custom;

import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

public class DateRangeNotNullException extends APIException {
    public DateRangeNotNullException(String message) {
        super(message);
    }

    public DateRangeNotNullException(APIExceptionType type, String code, String message) {
        super(type, code, message, true, null);
    }
}
