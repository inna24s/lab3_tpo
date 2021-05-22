package tpoSelenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploaderPage extends Page{
    public UploaderPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        driver.get("https://fastpic.ru/fpuploader");

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("fp uploader");
            }
        });

        return driver.getTitle();
    }
}
