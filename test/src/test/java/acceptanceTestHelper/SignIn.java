package acceptanceTestHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SignIn {
    public static void signIn(WebDriver driver){
        WebElement element = driver.findElement(By.id("logInForm:userName"));
        element.clear();
        element.sendKeys("foo");
        element = driver.findElement(By.id("logInForm:password"));
        element.clear();
        element.sendKeys("bar");
        element = driver.findElement(By.id("logInForm:logInButton"));
        element.click();
    }
}
