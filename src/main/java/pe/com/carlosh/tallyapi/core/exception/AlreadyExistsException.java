package pe.com.carlosh.tallyapi.core.exception;

public class AlreadyExistsException extends RuntimeException {
    private final String field;

    public AlreadyExistsException(String message) {
        super(message);
        this.field = null;
    }

    public AlreadyExistsException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}