import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tpoSelenium.Constants;
import tpoSelenium.Context;
import tpoSelenium.exceptions.PropertiesException;
import tpoSelenium.modules.ChangeImage;
import tpoSelenium.modules.Preview;
import tpoSelenium.utils.Properties;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageTest {
    private static final Logger logger = Logger.getLogger(PreviewTest.class);
    public ChangeImage changeImage;
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

        driverList.forEach(webDriver -> {
            webDriver.get("https://fastpic.ru/");
            webDriver.manage().window().setSize(new Dimension(1536, 824));

        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/rotate.csv", numLinesToSkip = 1)
    public void imageRotationTest(String path, String deg) {
        driverList.forEach(webDriver -> {
            webDriver.get("https://fastpic.ru/my.php");
            changeImage = new ChangeImage(webDriver);
            assertTrue(changeImage.rotatateImage(path, deg));
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/reduce.csv", numLinesToSkip = 1)
    public void imageReduceTest(String path, String size){
        driverList.forEach(webDriver -> {
            changeImage = new ChangeImage(webDriver);
            assertTrue(changeImage.reduceImage(path, size));

        });
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/paths.csv", numLinesToSkip = 1)
    public void imageJPEGTest(String path, String size){
        driverList.forEach(webDriver -> {
            changeImage = new ChangeImage(webDriver);
            assertTrue(changeImage.intoJPEG(path, size));
        });
    }

    @Test
    public void imagePosterTest(){
        driverList.forEach(webDriver -> {
            changeImage = new ChangeImage(webDriver);
            assertTrue(changeImage.intoPoster("https://clipart-best.com/img/mr-bean/mr-bean-clip-art-20.png"));
        });
    }
}
