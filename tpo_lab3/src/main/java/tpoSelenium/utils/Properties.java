package tpoSelenium.utils;

import org.apache.log4j.Logger;
import tpoSelenium.Constants;
import tpoSelenium.Context;
import tpoSelenium.exceptions.ChromeException;
import tpoSelenium.exceptions.FirefoxException;

import java.io.FileInputStream;
import java.io.IOException;


public class Properties {
    private static final Logger logger = Logger.getLogger(Properties.class);

    private static Properties instance;

    public static Properties getInstance() {
        if (instance == null) {
            instance = new Properties();
        }
        return instance;
    }

    public void reading(Context context) {
        java.util.Properties props = new java.util.Properties();

        try {
            props.load(new FileInputStream("browser.properties"));

            if (props.getProperty(Constants.WEBDRIVER_CHROME_DRIVER) != null) {
                if (props.getProperty(Constants.WEBDRIVER_CHROME_DRIVER).equals(Constants.CHROME_DRIVER)) {
                    context.setChromedriver(props.getProperty(Constants.WEBDRIVER_CHROME_DRIVER));
                }
            } else {
                throw new ChromeException();
            }

            if (props.getProperty(Constants.WEBDRIVER_FIREFOX_DRIVER) != null) {
                if (props.getProperty(Constants.WEBDRIVER_FIREFOX_DRIVER).equals(Constants.FIREFOX_FIREFOX)) {
                    context.setGeckodriver(props.getProperty(Constants.WEBDRIVER_FIREFOX_DRIVER));
                }
            } else {
                throw new FirefoxException();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
