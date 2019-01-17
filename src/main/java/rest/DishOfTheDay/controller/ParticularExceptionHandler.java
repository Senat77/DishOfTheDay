package rest.DishOfTheDay.controller;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rest.DishOfTheDay.util.ValidationUtil;
import java.time.Instant;


@RestControllerAdvice//(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ParticularExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(ParticularExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
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
            error = e.getClass().getSimpleName();

            if (logException) {
                log.error(error + " at request " + path, rootCause);
            } else {
                log.warn("{} at request  {}: {}", status, path, rootCause.toString());
            }
        }
    }
}
