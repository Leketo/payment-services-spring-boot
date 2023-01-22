package py.com.leketo.paymentServices.exceptions.handlers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import py.com.leketo.paymentServices.exceptions.constant.ErrorConstants;
import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;
import py.com.leketo.paymentServices.exceptions.models.ErrorBody;
import py.com.leketo.paymentServices.exceptions.utils.APIExceptionUtil;

import java.sql.SQLException;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
  private static Class _springDataAccessExceptionClass = null;

  static {
    try {
      _springDataAccessExceptionClass = Class.forName("org.springframework.dao.DataAccessException");
    } catch (ClassNotFoundException e) {
      log.debug("Spring classes not present");
    }
  }


  @ExceptionHandler(value = {Throwable.class})
  public ResponseEntity<ErrorBody> resourceNotFoundException(HttpServletRequest request, HttpServletResponse response, Throwable e) {
    log.error("Handling error", e);
    request.setAttribute("com.leketo.libcorecore.global.exceptions", e);
    ErrorBody eb = new ErrorBody();
    eb.setUseApiMessage(false); //todos son false, el unico caso que puede ser true es si del tipo APIException lo sobreescribe
    boolean statusSet = false;

    if (e instanceof APIException) {
      APIException ae = (APIException) e;
      eb.setCode(ae.getCode());
      eb.setMessage(ae.getMessage());
      eb.setType(ae.getType());
      eb.setUseApiMessage(ae.getUseApiMessage());
      response.setStatus(APIExceptionUtil.getStatusFromException(ae));
      statusSet = true;
      // MissingServletRequestParameterException
    } else if (e instanceof MissingServletRequestParameterException) {
      MissingServletRequestParameterException msrpe = (MissingServletRequestParameterException) e;
      eb.setCode(ErrorConstants.ERROR_MISSING_PARAMETER);
      eb.setMessage(String.format("Parameter [%s] of type [%s] is missing from request", msrpe.getParameterName(), msrpe.getParameterType()));
      eb.setType(APIExceptionType.APPLICATION);

    } else if (e instanceof BadCredentialsException) {
      eb.setCode(ErrorConstants.ERROR_UNEXPECTED);
      eb.setMessage("Credenciales invalida");
      eb.setType(APIExceptionType.APPLICATION);
    } else if (e instanceof DisabledException) {
      eb.setCode(ErrorConstants.ERROR_UNEXPECTED);
      eb.setMessage("Usuario deshabilitado");
      eb.setType(APIExceptionType.APPLICATION);
    } else if (e instanceof HttpMessageNotReadableException) {
      eb.setCode(ErrorConstants.ERROR_MISSING_INVALID_BODY_PARAMETER);
      eb.setMessage("Parametro del body invalido o inexistente: " + e.getMessage());
      eb.setType(APIExceptionType.APPLICATION);
    } else if (e instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException manve = (MethodArgumentNotValidException) e;
      eb.setCode(ErrorConstants.ERROR_MISSING_PARAMETER);
      BindingResult result = manve.getBindingResult();
      List<FieldError> fieldErrors = result.getFieldErrors();
      eb.setMessage(String.format("Body with error. %s", getBodyErrors(fieldErrors)));
      eb.setType(APIExceptionType.APPLICATION);
    } else if (e instanceof HttpRequestMethodNotSupportedException) {
      HttpRequestMethodNotSupportedException mnse = (HttpRequestMethodNotSupportedException) e;
      eb.setCode(ErrorConstants.ERROR_HTTP_INVALID_METHOD);
      eb.setMessage(String.format("Method [%s] is not supported for this service", mnse.getMethod()));
      eb.setType(APIExceptionType.INTERNAL);
      response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
      statusSet = true;
    } else if (e instanceof NoHandlerFoundException) {
      NoHandlerFoundException nhfe = (NoHandlerFoundException) e;
      eb.setCode(ErrorConstants.ERROR_HTTP_INVALID_METHOD);
      eb.setMessage(String.format("No handler found for url [%s] and method [%s]", nhfe.getRequestURL(), nhfe.getHttpMethod()));
      eb.setType(APIExceptionType.APPLICATION);
      response.setStatus(HttpStatus.NOT_FOUND.value());
      statusSet = true;
    } else if (e instanceof HttpMediaTypeException) {
      eb.setCode(ErrorConstants.ERROR_UNEXPECTED_WEB);
      eb.setMessage("Unexpected WEB COMPONENT error: " + e.getMessage());
      eb.setType(APIExceptionType.INTERNAL);
    } else if (_springDataAccessExceptionClass != null && _springDataAccessExceptionClass.isAssignableFrom(e.getClass())) {
      eb.setCode(ErrorConstants.ERROR_UNEXPECTED_DB);
      eb.setMessage("Unexpected DATABASE error");
      eb.setType(APIExceptionType.DATABASE);
    } else if (e instanceof SQLException) {
      eb.setCode(ErrorConstants.ERROR_UNEXPECTED_DB);
      eb.setMessage("Error inesperado de la DB");
      eb.setType(APIExceptionType.DATABASE);
    } else {
      eb.setCode(ErrorConstants.ERROR_UNEXPECTED);
      eb.setMessage("Ups. Error inesperado del servidor");
      eb.setType(APIExceptionType.INTERNAL);
    }

    if (!statusSet) {
      switch (eb.getType()) {
        case APPLICATION:
          response.setStatus(HttpStatus.BAD_REQUEST.value());
          break;
        case SECURITY:
          response.setStatus(HttpStatus.UNAUTHORIZED.value());
          break;
        case COMMUNICATION:
        case DATABASE:
        case INTERNAL:
          response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
          break;
      }
    }
    return new ResponseEntity<ErrorBody>(eb, HttpStatus.NOT_FOUND);
  }

  private String getBodyErrors(List<FieldError> fieldErrors) {
    StringBuilder errors = new StringBuilder();

    for (FieldError fieldError : fieldErrors) {
      errors.append("Attribute [" + fieldError.getField() + "] : [" + fieldError.getDefaultMessage() + "]. ");
    }

    return errors.toString();
  }
}