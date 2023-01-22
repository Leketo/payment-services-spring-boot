package py.com.leketo.paymentServices.exceptions.errors;


import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;

public class APIException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private APIExceptionType type;
    private String code;
    private Boolean useApiMessage;
    private Throwable cause;

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIException(APIExceptionType type, String code, String message, Boolean useApiMessage, Throwable cause) {
        super(message);
        setType(type);
        setCode(code);
        setUseApiMessage(useApiMessage);
    }

    public APIException(APIExceptionType type, String code, String message, Throwable cause) {
        this(type, code, message, false, cause);
    }


    public APIException(APIExceptionType type, String code, String message) {
        this(type, code, message, true, null);
    }

    public APIException(APIExceptionType type, String code, String message, Boolean useApiMessage) {
        this(type, code, message, useApiMessage, null);
    }

    public APIException(APIExceptionType type, String code) {
        this(type, code, null, false, null);
    }

    public APIException(APIExceptionType type, String code, Boolean useApiMessage) {
        this(type, code, null, useApiMessage, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public APIExceptionType getType() {
        return type;
    }

    public void setType(APIExceptionType type) {
        this.type = type;
    }

    public Boolean getUseApiMessage() {
        return useApiMessage;
    }

    public void setUseApiMessage(Boolean useApiMessage) {
        this.useApiMessage = useApiMessage;
    }
    
}