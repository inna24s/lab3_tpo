package tpoSelenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Image extends BasicModule{
    public Image(WebDriver webDriver){
        super(webDriver);
    }

    public void downloadImage(String path){
        webDriver.get("https://fastpic.ru/");
        webDriver.findElement(By.xpath("//a[contains(text(),\'[ по ссылке ]\')]")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).sendKeys(path);
        webDriver.findElement(By.xpath("//a[contains(text(),\'[ отключить все эффекты ]\')]")).click();
    }

    public static boolean isNumeric(String str)
    {
        try { int d = Integer.parseInt(str); }
        catch(NumberFormatException nfe) { return false; }
        return true;
    }
}
