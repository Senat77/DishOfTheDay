package rest.DishOfTheDay.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class IllegalMenuSetOfPollException extends RuntimeException {
}
