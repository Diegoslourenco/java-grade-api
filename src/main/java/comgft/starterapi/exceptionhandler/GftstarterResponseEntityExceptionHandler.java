package comgft.starterapi.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GftstarterResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String messageUser = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String messageDeveloper;
		
		if (ex.getCause() == null) {
			messageDeveloper = ex.toString();
		}
		else {
			messageDeveloper = ex.getCause().toString();
		}
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDeveloper));
		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = createErrorsList(ex.getBindingResult());
		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
		
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		String messageUser = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDeveloper));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		
		String messageUser = messageSource.getMessage("resource.not-permitted-operation", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ExceptionUtils.getRootCauseMessage(ex);
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDeveloper));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ StarterUsernameNotUniqueException.class })
	public ResponseEntity<Object> handleStarterUsernameNotUniqueException(StarterUsernameNotUniqueException ex, WebRequest request) {
		
		String messageUser = messageSource.getMessage("starter.username-not-unique", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDeveloper));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ StarterEmailNotUniqueException.class })
	public ResponseEntity<Object> handleStarterEmailNotUniqueException(StarterEmailNotUniqueException ex, WebRequest request) {
		
		String messageUser = messageSource.getMessage("starter.email-not-unique", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.toString();
		
		List<Error> errors = Arrays.asList(new Error(messageUser, messageDeveloper));
		
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private List<Error> createErrorsList(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			
			String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String messageDeveloper = fieldError.toString();
			
			errors.add(new Error(messageUser, messageDeveloper));
		}
	
		return errors;
	}
	
}
