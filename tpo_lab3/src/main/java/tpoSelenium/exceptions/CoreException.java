package tpoSelenium.exceptions;

public class CoreException extends IllegalArgumentException {

    protected String message;

    public CoreException(String message) {
        this.message = message;
    }

    public CoreException() {
        message = "Invalid.";
    }

    public String getMessage() {
        return message;
    }
}