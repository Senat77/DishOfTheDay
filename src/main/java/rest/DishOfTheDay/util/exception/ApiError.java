package rest.DishOfTheDay.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private LocalDateTime dt = LocalDateTime.now();

    private HttpStatus status;

    private String message;

    private String debugMessage;

    private List<FieldValidationError> fieldValidationErrors;

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }


    public void addValidationErrors(List<FieldError> fieldErrors) {
    }
}
