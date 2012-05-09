package acceptanceTestHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegisterLoad {
    public static void registerLoad(WebDriver driver, String content, String harbor, String destination){
        WebElement element = driver.findElement(By.id("registerLoadForm:contentInput"));
        element.clear();
        element.sendKeys(content);
        element = driver.findElement(By.id("registerLoadForm:harborInput"));
        element.clear();
        element.sendKeys(harbor);
        element = driver.findElement(By.id("registerLoadForm:destinationInput"));
        element.clear();
        element.sendKeys(destination);
        element = driver.findElement(By.id("registerLoadForm:registerLoadButton")) ;
        element.click();
    }
}
