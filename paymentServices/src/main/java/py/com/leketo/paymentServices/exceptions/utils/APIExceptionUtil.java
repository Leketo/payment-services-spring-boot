package py.com.leketo.paymentServices.exceptions.utils;


import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import py.com.leketo.paymentServices.exceptions.constant.ErrorConstants;
import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;

import java.sql.SQLException;

public class APIExceptionUtil {

    public static APIException getUnexpectedInternalException(String intlMsg)
    {
        return APIExceptionUtil.getUnexpectedInternalException(intlMsg, null);
    }

    public static APIException getUnexpectedInternalException(String intlMsg, Exception e)
    {
        return new APIException(
                APIExceptionType.INTERNAL,
                ErrorConstants.ERROR_UNEXPECTED,
                intlMsg,
                false,
                e
        );
    }

    public static APIException getUnexpectedDBException(String intlMsg, Exception e)
    {
        return new APIException(
                APIExceptionType.DATABASE,
                ErrorConstants.ERROR_UNEXPECTED_DB,
                intlMsg,
                false,
                e
        );
    }

    public static APIException getNotFoundException(String intlMsg)
    {
        return APIExceptionUtil.getNotFoundException(intlMsg, null);

    }

    public static APIException getNotFoundException(String intlMsg, Exception e)
    {
        return new APIException(
                APIExceptionType.APPLICATION,
                ErrorConstants.ERROR_ENTITY_NOT_FOUND,
                intlMsg,
                false,
                e
        );
    }

    public static APIException getApplicationExceptionWithUserMessage(String errorCode, String message) {
        return new APIException(APIExceptionType.APPLICATION, errorCode, message, true);
    }

    public static APIException getInternalSecurityException(String errorCode, String message) {
        return new APIException(APIExceptionType.SECURITY, errorCode, message);
    }

    public static int getStatusFromException(APIException ae) {
        String code = ae.getCode();
        if (ae == null)
            return HttpStatus.BAD_REQUEST.value();
        if (ae.getType() == APIExceptionType.DATABASE || ae.getType() == APIExceptionType.APPLICATION) {
            if (ae.getCode().equalsIgnoreCase(ErrorConstants.ERROR_DB_NOT_FOUND))
                return HttpStatus.NOT_FOUND.value();
            else if (ae.getCode().equalsIgnoreCase(ErrorConstants.ERROR_ENTITY_NOT_FOUND))
                return HttpStatus.NOT_FOUND.value();
        }
        return HttpStatus.BAD_REQUEST.value();
    }



}
