package rest.DishOfTheDay.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rest.DishOfTheDay.util.exception.NotFoundEntityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ParticularExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundEntityException.class)
    protected ResponseEntity<ParticularException> handleNotFoundEntityException(HttpServletRequest request, NotFoundEntityException exception) {

        return new ResponseEntity<>(
                new ParticularException(
                        request.getDateHeader(HttpHeaders.DATE),
                        HttpStatus.NOT_FOUND.value(),
                        "There is no such " + exception.getClazz().getSimpleName(),
                        request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    /*
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ParticularException> handleNotFoundEntityException(HttpServletRequest request, NotFoundEntityException exception) {

        return new ResponseEntity<>(
                new ParticularException(
                        HttpStatus.NOT_FOUND.value(),
                        request.,
                        request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }
    */

    @Data
    @AllArgsConstructor
    private static class ParticularException {
        private Long timestamp;
        private Integer status;
        private String message;
        private String path;
    }
}
