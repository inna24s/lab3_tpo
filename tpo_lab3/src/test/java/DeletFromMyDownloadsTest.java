import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import tpoSelenium.Constants;
import tpoSelenium.Context;
import tpoSelenium.exceptions.PropertiesException;
import tpoSelenium.modules.AddImage;
import tpoSelenium.utils.Properties;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeletFromMyDownloadsTest {
  int firstLength, secondLength;
  private static final Logger logger = Logger.getLogger(DeletFromMyDownloadsTest.class);
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

    if (context.getGeckodriver() != null) {
      System.setProperty(Constants.WEBDRIVER_FIREFOX_DRIVER, "C:\\Users\\inna-\\Desktop\\tpo_lab3\\geckodriver.exe");
      driverList.add(new FirefoxDriver());
    }
    if (context.getChromedriver() != null) {
      System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, "C:\\Users\\inna-\\Desktop\\tpo_lab3\\chromedriver.exe");
      driverList.add(new ChromeDriver());
    }

    if (driverList.isEmpty()) throw new PropertiesException();
  }

  @Test
  public void deletFromMyDownloads() {
    driverList.forEach(webDriver -> {
      webDriver.get("https://fastpic.ru/my.php");
      webDriver.manage().window().setSize(new Dimension(968, 824));
      firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
      //если в корзине ничего нет, добавим картинок
      if (firstLength == 0) {
        AddImage image = new AddImage(webDriver);
        image.addImageByLink("https://www.ejin.ru/wp-content/uploads/2017/09/3-1282.jpg");
        image.addImageByLink("https://attuale.ru/wp-content/uploads/2018/10/1111-1.jpg");
        image.addImageByLink("https://bipbap.ru/wp-content/uploads/2015/04/a_young_female_gorilla-1920x1200.jpg");
      }
      webDriver.get("https://fastpic.ru/my.php");
      secondLength= webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
      System.out.println(secondLength);
      //удалить 1 картинку
      webDriver.findElement(By.xpath("//form[@id='manage-form']/div[2]/div/label/input")).click();
      webDriver.findElement(By.id("delete_checked")).click();
      assertEquals(secondLength - 1, webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size());
      //удалить все
      if (webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size() == 0) {
        AddImage image = new AddImage(webDriver);
        image.addImageByLink("https://www.ejin.ru/wp-content/uploads/2017/09/3-1282.jpg");
        image.addImageByLink("https://attuale.ru/wp-content/uploads/2018/10/1111-1.jpg");
        image.addImageByLink("https://bipbap.ru/wp-content/uploads/2015/04/a_young_female_gorilla-1920x1200.jpg");
      }
      webDriver.get("https://fastpic.ru/my.php");
      webDriver.findElement(By.xpath("//a[contains(text(),\'[ выбрать всё ]\')]")).click();
      webDriver.findElement(By.xpath("//a[contains(text(),\'[ удалить выбранное ]\')]")).click();
      assertEquals(0, webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size());
    });
  }
}

