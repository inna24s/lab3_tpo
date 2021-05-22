package tpoSelenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RoolsPage extends Page {
    public RoolsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        driver.get("https://fastpic.ru/rules");

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("правила");
            }
        });

        return driver.getTitle();
    }
}
