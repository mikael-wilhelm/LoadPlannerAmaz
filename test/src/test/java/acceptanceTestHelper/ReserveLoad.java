package acceptanceTestHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReserveLoad {
    public static void reserveLoad(String content, WebDriver driver){
        WebElement element = driver.findElement(By.xpath("//tr[contains(.,'coal')]//input[@value='Book']"));
        element.click();

    }
}
