import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tpoSelenium.Constants;
import tpoSelenium.Context;
import tpoSelenium.exceptions.PropertiesException;
import tpoSelenium.modules.Preview;
import tpoSelenium.utils.Properties;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PreviewTest {
    private static final Logger logger = Logger.getLogger(PreviewTest.class);
    public Preview preview;
    public Context context;
    public List<WebDriver> driverList;
    int firstLength, secondLength;
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
    @Test
    public void previewTest1(){
        driverList.forEach(webDriver -> {
            webDriver.get("https://fastpic.ru/my.php");
            firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
            webDriver.get("https://fastpic.ru/");
            webDriver.manage().window().setSize(new Dimension(1536, 824));
            preview = new Preview(webDriver);
            //Надпись без размера
            preview.addImageWithPreview1("https://i.artfile.ru/2880x1620_1394646_[www.ArtFile.ru].jpg", "обезьяна");
            webDriver.get("https://fastpic.ru/my.php");
            secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
            assertTrue(secondLength == firstLength + 1);

            webDriver.get("https://fastpic.ru/");
            //размер без надписи
            preview.addImageWithPreview1("https://www.anypics.ru/download.php?file=201210/1440x900/anypics.ru-10865.jpg", "100");
            webDriver.get("https://fastpic.ru/my.php");
            secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
            assertTrue(secondLength == firstLength + 2);

            webDriver.quit();
        });
    }

    @Test
    public void previewTestEmpty(){
        driverList.forEach(webDriver -> {
            //без надписи и размера
            webDriver.get("https://fastpic.ru/my.php");
            firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
            webDriver.get("https://fastpic.ru/");
            webDriver.manage().window().setSize(new Dimension(1536, 824));
            preview = new Preview(webDriver);
            preview.addImageWithPreviewEmpty("https://www.nastol.com.ua/download.php?img=201207/1440x900/nastol.com.ua-28889.jpg");
            webDriver.get("https://fastpic.ru/my.php");
            secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
            assertTrue(secondLength == firstLength + 1);
            webDriver.quit();
        });
    }

    @Test
    public void previewTest2(){
        driverList.forEach(webDriver -> {
            webDriver.get("https://fastpic.ru/my.php");
            firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
            webDriver.get("https://fastpic.ru/");
            webDriver.manage().window().setSize(new Dimension(1536, 824));
            preview = new Preview(webDriver);
            //Размер+надпись
            preview.addImageWithPreview2("https://img11.postila.ru/data/33/ad/12/63/33ad1263c8a43bf72278fd72344811c3caae139faccd29851d37582454a6ad90.jpg", "бе-бе-бе", "150");
            webDriver.get("https://fastpic.ru/my.php");
            secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
            assertTrue(secondLength == firstLength + 1);

            webDriver.get("https://fastpic.ru/");
            //размер + надпись размера
            preview.addImageWithPreview2("https://crosti.ru/patterns/00/1d/62/eac72a1fad/picture.jpg", "", "50");
            webDriver.get("https://fastpic.ru/my.php");
            secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
            assertTrue(secondLength == firstLength + 2);

            webDriver.quit();
        });
    }
}
