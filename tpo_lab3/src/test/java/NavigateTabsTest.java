// Generated by Selenium IDE

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import tpoSelenium.Constants;
import tpoSelenium.Context;
import tpoSelenium.exceptions.PropertiesException;
import tpoSelenium.pages.*;
import tpoSelenium.utils.Properties;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigateTabsTest {
  private static final Logger logger = Logger.getLogger(NavigateTabsTest.class);
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

//    if (context.getGeckodriver() != null) {
//      System.setProperty(Constants.WEBDRIVER_FIREFOX_DRIVER, "C:\\Users\\inna-\\Desktop\\tpo_lab3\\geckodriver.exe");
//      driverList.add(new FirefoxDriver());
//    }
    if (context.getChromedriver() != null) {
      System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, "C:\\Users\\inna-\\Desktop\\tpo_lab3\\chromedriver.exe");
      driverList.add(new ChromeDriver());
    }

    if (driverList.isEmpty()) throw new PropertiesException();
  }
  @Test
  public void navigateTabs() {
    driverList.forEach(webDriver -> {
      webDriver.get("https://fastpic.ru/");
      webDriver.manage().window().setSize(new Dimension(968, 824));
      webDriver.findElement(By.xpath("//*[@id=\"headermenu\"]/tbody/tr/td/div/a[1]/div/div[4]")).click();
      assertEquals("FastPic — Загрузить изображения", PageFactory.initElements(webDriver, MainPage.class)
              .getTitle());
      webDriver.findElement(By.xpath("//table[@id=\'headermenu\']/tbody/tr/td/div/a[2]/div/div[4]")).click();
      assertEquals("Правила — FastPic", PageFactory.initElements(webDriver, RoolsPage.class)
              .getTitle());
      webDriver.findElement(By.xpath("//table[@id=\'headermenu\']/tbody/tr/td/div/a[3]/div/div[4]")).click();
      assertEquals("О сервисе — FastPic", PageFactory.initElements(webDriver, AboutPage.class)
              .getTitle());
      webDriver.findElement(By.xpath("//table[@id=\'headermenu\']/tbody/tr/td/div/a[4]/div/div[4]")).click();
      assertEquals("FP Uploader программа загрузки изображений — FastPic", PageFactory.initElements(webDriver, UploaderPage.class)
              .getTitle());
      webDriver.findElement(By.xpath("//table[@id=\'headermenu\']/tbody/tr/td/div/a[5]/div/div[4]")).click();
      assertEquals("Мои загрузки — FastPic", PageFactory.initElements(webDriver, DownloadsPage.class)
              .getTitle());
      webDriver.quit();
    });


  }
}
