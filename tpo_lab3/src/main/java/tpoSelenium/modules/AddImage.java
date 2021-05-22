package tpoSelenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class AddImage extends BasicModule{
    public AddImage(WebDriver webDriver) {
        super(webDriver);
    }
    int firstLength, secondLength;

    public boolean addImageByLink(String path){
        webDriver.get("https://fastpic.ru/my.php");
        firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        webDriver.get("https://fastpic.ru");
        webDriver.manage().window().setSize(new Dimension(1536, 824));
        webDriver.findElement(By.xpath("//a[contains(text(),\'[ по ссылке ]\')]")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).click();
        webDriver.findElement(By.xpath("//textarea[@id=\'upload_files\']")).sendKeys(path);
        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
        webDriver.get("https://fastpic.ru/my.php");
        secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        return  secondLength == firstLength + 1;
    }

    public boolean addImageByComp(String path){
        webDriver.get("https://fastpic.ru/my.php");
        firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        webDriver.get("https://fastpic.ru");
        webDriver.manage().window().setSize(new Dimension(1536, 824));
        webDriver.findElement(By.xpath("//a[contains(text(),\'[ с компьютера ]\')]")).click();
        webDriver.findElement(By.xpath("/html/body/div[2]/table[2]/tbody/tr[2]/td/div/form/div/div[2]/input")).click();
        System.out.println("I'm here");
        webDriver.findElement(By.xpath("//*[@id=\'file\']")).sendKeys(path);
        webDriver.findElement(By.xpath("//input[@id=\'uploadButton\']")).click();
        webDriver.get("https://fastpic.ru/my.php");
        secondLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div")).size();
        System.out.println(secondLength);
        return secondLength == firstLength + 1;
    }

//    public boolean addSomeImageByLink(String[] path){
//        webDriver.get("https://fastpic.ru/my.php");
//        firstLength = webDriver.findElements(By.xpath("//*[@id=\"manage-form\"]/div[1]")).size();
//        webDriver.get("https://fastpic.ru");
//        webDriver.manage().window().setSize(new Dimension(1536, 824));
//    }

}
