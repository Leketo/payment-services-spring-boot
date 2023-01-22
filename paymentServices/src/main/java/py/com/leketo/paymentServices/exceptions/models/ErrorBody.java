package py.com.leketo.paymentServices.exceptions.models;


import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;

public class ErrorBody {

	private String code;
	private String message;
	private APIExceptionType type;
	private Boolean useApiMessage;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	
	@Override
	public String toString() {
		return "ErrorBody [code=" + code + ", message=" + message + ", type=" + type.toString() + ", useApiMessage="
				+ useApiMessage + "]";
	}
}