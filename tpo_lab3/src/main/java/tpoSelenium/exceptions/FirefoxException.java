package tpoSelenium.exceptions;

public class FirefoxException extends CoreException {

    public FirefoxException(String message) {
        super(message);
    }

    public FirefoxException() {
        super.message = "Configuration for running Firefox not found.";
    }
}
