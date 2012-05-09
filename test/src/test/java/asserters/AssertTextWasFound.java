package asserters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AssertTextWasFound {

    public static void assertTextWasFound(WebDriver driver, String content, String harbor, String destination){
            ArrayList<String> expectedText = new ArrayList<String>();
            expectedText.add(content);
            expectedText.add(harbor);
            expectedText.add(destination);

            String expectedNotFound = "";
            String resultNotFound =  getNotFoundText(expectedText,driver);

            assertThat(resultNotFound,is(expectedNotFound));
    }

    private static String getNotFoundText(ArrayList<String> expectedWords,WebDriver driver){
        String result = "";
        for(String expectedWord:expectedWords){
            try{
            driver.findElement(By.xpath("//td[contains(.,'" + expectedWord + "')]"));
            }
            catch (Exception ignored){
                result+= result + " " + expectedWord ;
            }
        }
        return result;
    }
}

