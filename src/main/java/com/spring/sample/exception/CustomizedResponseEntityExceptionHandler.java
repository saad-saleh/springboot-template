package com.spring.sample.exception;

import com.spring.sample.dto.exception.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Saad Saleh
 * The CustomizedResponseEntityExceptionHandler advice extends the ResponseEntityExceptionHandler to override the exception experience.
 *
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler // extends ResponseEntityExceptionHandler
{
    private Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    /**
     * This method deals with any unhandled exception to return the fault scheme.
     * @param ex The thrown exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleStandardExceptions(Exception ex, WebRequest request) {
        logger.debug("Exception>>Handle All Exceptions :");
        logger.info(ex.getClass().getName());
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
                request.getDescription(false),ErrorCode.SERVER_ERROR.getCode(),ErrorCode.SERVER_ERROR.getErrorCodeDescription(), new Date(),null);
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * This method handles any CustomException. Any exception thrown in the project should be handled by CustomException if the developer has a need to ad cutom logic to the exception handling (like error codes or custom http status).
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({CustomException.class,})
    public final ResponseEntity<Object> handleGenericCustomExceptions(CustomException ex, WebRequest request) {
        logger.debug("Exception>>Handle All Exceptions :");
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                ex.getErrorCode().getHttpStatus()
                , ex.getMessage()
                ,request.getDescription(false)
                ,ex.getErrorCode().getCode()
                ,ex.getErrorCode().getErrorCodeDescription()
                , new Date()
                ,null);
        return new ResponseEntity(exceptionResponse, ex.getErrorCode().getHttpStatus());
    }


    /**
     * This method handles any Binding exceptions which is the exceptions handled by spring-boot validations.
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({BindException.class, MissingServletRequestParameterException.class,MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MissingServletRequestParameterException ex, WebRequest request) {
        logger.debug("Exception>>Handle All Exceptions :");
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                HttpStatus.BAD_REQUEST
                , ex.getMessage()
                ,""
                ,ErrorCode.INVALID_INPUT.getCode()
                ,ErrorCode.INVALID_INPUT.getErrorCodeDescription()
                , new Date()
                ,getNotValidArguments(ex));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getNotValidArguments(Exception ex){
        Map<String, String> errors = new HashMap<>();
        if(ex instanceof MissingServletRequestParameterException){
            errors.put(((MissingServletRequestParameterException) ex).getParameterName(),((MissingServletRequestParameterException) ex).getBody().getDetail());
            return errors;
        }else if(ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException)ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }else return null;
    }


}
