package com.bicgraphic.ods.order.Exceptions;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bicgraphic.ods.order.constants.OdsConstants;
import com.bicgraphic.ods.order.model.EventResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<EventResponse> genericExceptionHandler(Exception exception) {
		EventResponse status = new EventResponse(OdsConstants.ORDER_UNKNOWN_ERR_CODE, OdsConstants.ORDER_UNKNOWN_ERR_MSG);
		return new ResponseEntity<EventResponse>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = OdsOrderException.class)
	public ResponseEntity<EventResponse> ExceptionHandler(OdsOrderException exception) {
		EventResponse status = new EventResponse(exception.getOrderErrorCode(), exception.getOrderErrorMessage());
		return new ResponseEntity<EventResponse>(status, HttpStatus.OK);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<String> validationErrorList = new ArrayList<String>();
		for (FieldError error : errors) {
			validationErrorList.add(error.getDefaultMessage());
		}
		EventResponse errorDetails = new EventResponse(OdsConstants.ORDER_MANDATORY_MISS_CODE, validationErrorList.toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
