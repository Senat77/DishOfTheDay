package rest.DishOfTheDay.util.exception;

public class NotFoundException extends RuntimeException {

    private Class aClass;

    public NotFoundException(String message, Class aClass) {
        super(message);
        this.aClass = aClass;
    }
}