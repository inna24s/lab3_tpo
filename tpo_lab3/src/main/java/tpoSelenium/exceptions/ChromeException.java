package tpoSelenium.exceptions;

public class ChromeException extends CoreException {

    public ChromeException(String message) {
        super(message);
    }

    public ChromeException() {
        super.message = "Configuration for running Chrome not found.";
    }

}
