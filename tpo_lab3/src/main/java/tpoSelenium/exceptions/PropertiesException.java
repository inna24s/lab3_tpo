package tpoSelenium.exceptions;

public class PropertiesException extends CoreException {

    public PropertiesException(String message) {
        super(message);
    }

    public PropertiesException() {
        super.message = "Empty file.";
    }
}
