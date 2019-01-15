package rest.DishOfTheDay.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no find entity")
public class NotFoundEntityException extends RuntimeException {

    private Class clazz;

    public NotFoundEntityException(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
