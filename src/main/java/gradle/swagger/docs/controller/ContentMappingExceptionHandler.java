package gradle.swagger.docs.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gradle.swagger.docs.dto.ErrorDto;
import gradle.swagger.docs.utils.Constants;

/**
 * @author SSHABBIR
 *
 */
@ControllerAdvice
public class ContentMappingExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex) {
	Locale locale = LocaleContextHolder.getLocale();
	String code = ex.getBindingResult().getFieldError().getDefaultMessage();
	ErrorDto errorDto = new ErrorDto(messageSource.getMessage(code, null, locale), ex.getMessage());
	return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleValidationException(DataIntegrityViolationException ex) {
	Locale locale = LocaleContextHolder.getLocale();
	String message = messageSource.getMessage(Constants.DATA_INTEGRITY_VIOLATION_EXCP, null, locale);
	ErrorDto errorDto = new ErrorDto(message, ex.getMessage());
	return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDto> badRequestExceptionHandler(final Throwable throwable) {
	String errorMessage = throwable != null ? throwable.getMessage() : "Unknown error";
	ErrorDto errorDto = new ErrorDto();
	errorDto.setException(errorMessage);
	return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler to catch all exceptions (Throwable) that are not have
     * specific handler
     *
     * @param throwable
     * @param model
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDto> genericExceptionHandler(final Throwable throwable) {
	String errorMessage = throwable != null ? throwable.getMessage() : "Unknown error";
	ErrorDto errorDto = new ErrorDto();
	errorDto.setException(errorMessage);
	return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
