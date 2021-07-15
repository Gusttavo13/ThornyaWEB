package club.thornya.thornyaweb.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Senders {

    public static void typePhoto(String path, String description) throws InterruptedException{

        DriverSetup.getDriver().findElement(By.cssSelector("span[data-icon='clip']")).click();

        DriverSetup.getDriver().findElement(By.cssSelector("input[type='file']")).sendKeys(path);
        TimeUnit.SECONDS.sleep(2);
        DriverSetup.getDriver().findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div[2]/div[2]/span/div[1]/span/div[1]/div/div[2]/div[1]/span/div/div[2]/div/div[3]/div[1]/div[2]")).sendKeys(description);
        TimeUnit.SECONDS.sleep(2);
        DriverSetup.getDriver().findElement(By.cssSelector("span[data-icon='send']")).click();
        TimeUnit.SECONDS.sleep(1);
    }

    public static void typeFile(String path) throws InterruptedException{

        DriverSetup.getDriver().findElement(By.cssSelector("span[data-icon='clip']")).click();

        DriverSetup.getDriver().findElement(By.cssSelector("input[type='file']")).sendKeys(path);
        TimeUnit.SECONDS.sleep(2);

        DriverSetup.getDriver().findElement(By.cssSelector("span[data-icon='send']")).click();
        TimeUnit.SECONDS.sleep(1);
    }


    public static void typeChat(String mensagem) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        WebElement chat = DriverSetup.getDriver().findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div[2]/div/div[2]"));
        chat.sendKeys(mensagem + Keys.ENTER);
    }

    public static void typeChat(String[] mensagem) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        WebElement chat = DriverSetup.getDriver().findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div[2]/div/div[2]"));
        for (String msg : mensagem){
            chat.sendKeys(msg + Keys.SHIFT + Keys.ENTER);
        }
        chat.sendKeys(Keys.ENTER);
    }
    public static void typeChat(ArrayList<String> mensagem) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        WebElement chat = DriverSetup.getDriver().findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div[2]/div/div[2]"));
        for (String msg : mensagem){
            chat.sendKeys(msg + Keys.SHIFT + Keys.ENTER);
        }
        chat.sendKeys(Keys.ENTER);
    }

}
