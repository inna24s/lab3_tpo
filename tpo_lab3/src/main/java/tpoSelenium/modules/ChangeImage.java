package tpoSelenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeImage extends BasicModule{
    public ChangeImage(WebDriver webDriver){
        super(webDriver);
    }
    int firstLength, secondLength;
    public Image image;
    public boolean rotatateImage (String path, String deg){
        webDriver.get("https://fastpic.ru/my.php");
        firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        image = new Image(webDriver);
        image.downloadImage(path);
        webDriver.findElement(By.xpath("//input[@id=\'check_orig_rotate\']")).click();
        webDriver.findElement(By.xpath("//select[@id=\'orig-rotate\']")).click();
        {
            WebElement dropdown = webDriver.findElement(By.id("orig-rotate"));
            dropdown.findElement(By.xpath("//option[. = '"+deg+"']")).click();
        }
        webDriver.findElement(By.xpath("//select[@id=\'orig-rotate\']")).click();
        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
        webDriver.get("https://fastpic.ru/my.php");
        secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        return secondLength == firstLength+1;
    }

    public boolean reduceImage(String path, String pix) {
        webDriver.get("https://fastpic.ru/my.php");
        firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        image = new Image(webDriver);
        image.downloadImage(path);
        webDriver.findElement(By.xpath("//input[@id=\'check_orig_resize\']")).click();
        if (pix.contains(" (")) {
            webDriver.findElement(By.xpath("//select[@id=\'orig-resize-helper\']")).click();
            {
                WebElement dropdown = webDriver.findElement(By.id("orig-resize-helper"));
                dropdown.findElement(By.xpath("//option[. = '"+pix+"']")).click();
            }
            webDriver.findElement(By.xpath("//select[@id=\'orig-resize-helper\']")).click();
        }
        else {
            webDriver.findElement(By.xpath("//input[@id=\'check_orig_resize\']")).click();
            webDriver.findElement(By.cssSelector(".f-image-opts")).click();
            webDriver.findElement(By.xpath("//input[@id=\'orig-resize\']")).clear();
            webDriver.findElement(By.xpath("//input[@id=\'orig-resize\']")).sendKeys(pix);
        }
        webDriver.findElement(By.id("check_thumb")).click();
        webDriver.findElement(By.id("thumb-size")).click();
        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
        webDriver.get("https://fastpic.ru/my.php");
        System.out.println(webDriver.findElement(By.xpath("//*[@id=\"manage-form\"]/div[1]/a/img")).getAttribute("width"));
        secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        if(secondLength == firstLength+1){
            return (
                    webDriver.findElement(By.xpath("//*[@id=\"manage-form\"]/div[1]/a/img")).getAttribute("src").endsWith("jpeg")
            );
        }
        return false;
    }

    //изображение будет сжато в jpeg
    public boolean intoJPEG(String path, String percent){
        webDriver.get("https://fastpic.ru/my.php");
        firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        image = new Image(webDriver);
        image.downloadImage(path);
        webDriver.findElement(By.cssSelector(".optimization > label")).click();
        webDriver.findElement(By.cssSelector(".optimization")).click();
        webDriver.findElement(By.xpath("//input[@id=\'jpeg-quality\']")).sendKeys("60");
        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
        webDriver.get("https://fastpic.ru/my.php");
        secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        if(secondLength == firstLength+1){
            return (
                    webDriver.findElement(By.xpath("//*[@id=\"manage-form\"]/div[1]/a/img")).getAttribute("src").endsWith("jpeg")
            );
        }
        return false;
    }

    //изображение будет сжато в jpeg и ограничено размером 1200px по большей стороне
    public boolean intoPoster(String path){
        webDriver.get("https://fastpic.ru/my.php");
        firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        image = new Image(webDriver);
        image.downloadImage(path);
        webDriver.findElement(By.xpath("//input[@name=\'check_poster\']")).click();
        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
        webDriver.get("https://fastpic.ru/my.php");
        secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        if(secondLength == firstLength+1){
            return (
                    webDriver.findElement(By.xpath("//*[@id=\"manage-form\"]/div[1]/a/img")).getAttribute("src").endsWith("jpeg")
            );
        }
         return false;
    }

}
