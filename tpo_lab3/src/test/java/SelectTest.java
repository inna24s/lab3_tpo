import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import tpoSelenium.exceptions.PropertiesException;
import tpoSelenium.modules.AddImage;
import tpoSelenium.utils.Properties;
import tpoSelenium.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SelectTest {
    private static final Logger logger = Logger.getLogger(SelectTest.class);
    public AddImage image;
    public Context context;
    public List<WebDriver> driverList;
    @BeforeEach
    public void setUp() {
        context = new Context();
        driverList = new ArrayList<>();

        try {
            Properties.getInstance().reading(context);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }
        if (context.getChromedriver() != null) {
            System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, "C:\\Users\\inna-\\Desktop\\tpo_lab3\\chromedriver.exe");
            driverList.add(new ChromeDriver());
        }

//        if (context.getGeckodriver() != null) {
//            System.setProperty(Constants.WEBDRIVER_FIREFOX_DRIVER, "C:\\Users\\inna-\\Desktop\\tpo_lab3\\geckodriver.exe");
//            driverList.add(new FirefoxDriver());
//        }

        if (driverList.isEmpty()) throw new PropertiesException();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/links.csv", numLinesToSkip = 1)
    public void bylink(String link) {
        driverList.forEach(webDriver -> {
            image = new AddImage(webDriver);
            assertTrue(image.addImageByLink(link));
            //wrong link
            assertFalse(image.addImageByLink("https://www.ejin.rup-content/uploads/2017/09/3-1282.jpg"));
            webDriver.quit();
        });
    }

    @Test
    public void byComp(){
        driverList.forEach(webDriver -> {
            image = new AddImage(webDriver);
            assertTrue(image.addImageByComp("C:\\Users\\inna-\\Desktop\\3 курс\\2 семестр\\ТПО\\labs\\lab3\\Схема.png"));
            webDriver.quit();
        });
    }
}
