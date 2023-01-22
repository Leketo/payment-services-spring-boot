package py.com.leketo.paymentServices.exceptions.constant;

public class ErrorConstants {

	// global error
	public static final String ERROR_UNEXPECTED = "g1000";
	public static final String ERROR_UNEXPECTED_DB = "g1005";
	public static final String ERROR_UNEXPECTED_WEB = "g1008";
	public static final String ERROR_API_KEY_EXPECTED = "g1010";
	public static final String ERROR_ACCESS_TOKEN_EXPECTED = "g1011";
	public static final String ERROR_CONTEXT_NOT_FOUND = "g1020";
	public static final String ERROR_CANT_GENERATE_OUTPUT = "g1030";
	public static final String ERROR_ENTITY_NOT_FOUND = "g1040";
	public static final String ERROR_MISSING_PARAMETER = "g1050";
	public static final String ERROR_INVALID_PARAMETER = "g1060";

	
	// global format errors
	public static final String ERROR_INVALID_ISO8601_DATE = "gf1000";
	public static final String ERROR_INVALID_DATABAG_FORMAT = "gf1010";
	public static final String ERROR_INVALID_JSON_FORMAT = "gf1011";

	// global http errors
	public static final String ERROR_HTTP_INVALID_METHOD = "gh1010";
	public static final String ERROR_MISSING_INVALID_BODY_PARAMETER = "gh1011";
	public static final String ERROR_HTTP_NOT_FOUND = "gh1020";
	
	// global database errors
	public static final String ERROR_DB_DUPLICATE_KEY = "gdb1000";
	public static final String ERROR_DB_NOT_FOUND = "gdb1010";
	
	//acl
	public static final String ERROR_ACL_INVALID_RULE_TYPE= "acl1005";
	public static final String ERROR_ACL_OBJECT_EXISTENTE= "acl1015";
	public static final String ERROR_ACL_RELACION_EXISTENTE= "acl1105";
	
	//security
	public static final String ERROR_SEC_INVALID_APIKEY         = "spd1000";
	public static final String ERROR_SEC_ACTION_NOT_ALLOWED     = "spd1001";
	public static final String ERROR_SEC_ACTION_UNDEFINED 	    = "spd1010";
	public static final String ERROR_MICHI_SESSION_REQUIRED     = "spd1020";
	public static final String ERROR_INVALID_MICHI_SESSION_TYPE = "spd1030";

	/*
	public static final String ERROR_INVALID_ACCESS_TOKEN_FORMAT = "auth1004";
	public static final String ERROR_INVALID_ACCESS_TOKEN = "auth1005";
	public static final String ERROR_INVALID_CLIENT_VERSION_TYPE = "auth1006";
	public static final String ERROR_INACTIVE_CLIENT_VERSION = "auth1008";
	public static final String ERROR_CLAVE_NO_REGISTRADA = "auth1009";
	public static final String ERROR_SIMULTANEOUS_ACCESS = "auth1010";
	public static final String ERROR_USER_BLOCKED = "auth1011";
	public static final String ERROR_INVALID_SESSION = "auth1012";
	public static final String ERROR_INVALID_USER = "auth1014";
	*/

	//registration
	public static final String ERROR_REG_URL_ALREADY_EXISTS = "reg1001";
	
}
