package rest.DishOfTheDay.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import rest.DishOfTheDay.domain.HasId;
import rest.DishOfTheDay.util.exception.IllegalRequestDataException;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.util.StringJoiner;

public class ValidationUtil {

    /*
    public static <T> T checkNotFoundWithId(T object, int id, Class aClass) {
        return checkNotFound(object, "id=" + id, aClass);
    }

    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id, Class aClass) {
        checkNotFound(found, "id=" + id, aClass);
    }

    public static <T> T checkNotFound(T object, String msg, Class aClass) {
        checkNotFound(object != null, msg, aClass);
        return object;
    }

    public static void checkNotFound(boolean found, String msg, Class aClass) {
        if (!found) {
            throw new NotFoundException(aClass);
        }
    }
    */

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
    //  http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static ResponseEntity<String> getErrorResponse(BindingResult result) {
        StringJoiner joiner = new StringJoiner("<br>");
        result.getFieldErrors().forEach(
                fe -> {
                    String msg = fe.getDefaultMessage();
                    if (msg != null) {
                        if (!msg.startsWith(fe.getField())) {
                            msg = fe.getField() + ' ' + msg;
                        }
                        joiner.add(msg);
                    }
                });
        return new ResponseEntity<>(joiner.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }
}