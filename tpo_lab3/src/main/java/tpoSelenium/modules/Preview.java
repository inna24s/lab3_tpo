package tpoSelenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Preview extends BasicModule{
    String note, size;
    public Preview(WebDriver webDriver){
        super(webDriver);
    }


    public void addImageWithPreviewEmpty(String path){
        webDriver.findElement(By.xpath("//a[contains(text(),\'[ по ссылке ]\')]")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).sendKeys(path);
        webDriver.findElement(By.cssSelector("label:nth-child(7) > #check_thumb")).click();

        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
    }

    public void addImageWithPreview1(String path, String noteOrSize){
        if (Image.isNumeric(noteOrSize)) this.size = noteOrSize;
        else this.note = noteOrSize;
        webDriver.findElement(By.xpath("//a[contains(text(),\'[ по ссылке ]\')]")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).sendKeys(path);
        if (size == null) {
                webDriver.findElement(By.cssSelector("label:nth-child(4) > #check_thumb")).click();
                webDriver.findElement(By.xpath("//input[@id=\'thumb_text\']")).click();
                webDriver.findElement(By.xpath("//input[@id=\'thumb_text\']")).sendKeys(note);
            }
        else {

                webDriver.findElement(By.cssSelector("label:nth-child(7) > #check_thumb")).click();
                webDriver.findElement(By.xpath("//input[@id=\'check-thumb-size-vertical\']")).click();
                webDriver.findElement(By.cssSelector(".preview-size")).click();
                webDriver.findElement(By.xpath("//input[@id=\'thumb-size\']")).sendKeys(size);
        }
        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
    }

    public void addImageWithPreview2(String path, String note, String size){
        webDriver.findElement(By.xpath("//a[contains(text(),\'[ по ссылке ]\')]")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).sendKeys(path);
        if (note.equals("")){
                webDriver.findElement(By.id("check_thumb")).click();
                webDriver.findElement(By.id("thumb-size")).click();
        }
        else {
               webDriver.findElement(By.cssSelector("label:nth-child(4) > #check_thumb")).click();
               webDriver.findElement(By.xpath("//input[@id=\'thumb_text\']")).click();
               webDriver.findElement(By.xpath("//input[@id=\'thumb_text\']")).sendKeys(note);
        }
        webDriver.findElement(By.id("thumb-size")).sendKeys(size);
        webDriver.findElement(By.id("check-thumb-size-vertical")).click();
        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
    }


}
