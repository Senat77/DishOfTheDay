package rest.DishOfTheDay.controller;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rest.DishOfTheDay.util.ValidationUtil;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.time.Instant;


@ControllerAdvice//(annotations = RestController.class)
//@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ParticularExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(ParticularExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(Exception ex, Object body, WebRequest request) {
        log.info("In handleNotFoundException");
        return new ResponseEntity<>(new ErrorInfo(request, ex, true, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> duplicateDataException(Exception ex, Object body, WebRequest request) {
        log.info("In duplicateDataException");
        return new ResponseEntity<>(new ErrorInfo(request, ex, true, HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("In handleTypeMismatch");
        return new ResponseEntity<>(new ErrorInfo(request, ex, true, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @Data
    public static class ErrorInfo {
        private Instant timestamp = Instant.now();
        private Integer status;
        private String error;
        private String message;
        private String path;

        ErrorInfo(WebRequest req, Exception e, boolean logException, HttpStatus status) {

            path = ((ServletWebRequest)req).getRequest().getServletPath();
            this.status = status.value();
            Throwable rootCause = ValidationUtil.getRootCause(e);
            message = rootCause.toString();

            if(e.getClass().equals(NotFoundException.class))
                error = "There is not such " + ((NotFoundException)e).getaClass().getSimpleName();
            else
                error = e.getClass().getSimpleName();

            if (logException) {
                log.error(error + " at request " + path, rootCause);
            } else {
                log.warn("{} at request  {}: {}", status, path, rootCause.toString());
            }
        }
    }
}
