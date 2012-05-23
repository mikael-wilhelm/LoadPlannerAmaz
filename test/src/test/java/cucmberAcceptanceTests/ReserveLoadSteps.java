package cucmberAcceptanceTests;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import acceptanceTestHelper.RegisterLoad;
import acceptanceTestHelper.ReserveLoad;
import acceptanceTestHelper.SignIn;
import asserters.AssertTextWasFound;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import databaseWrapper.DatabaseWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URISyntaxException;
import java.sql.SQLException;

public class ReserveLoadSteps {

    private WebDriver driver;
    String actualContent;
    String actualHarbor;
    String actualDestination;

    @Before
    public void init() throws SQLException, URISyntaxException {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/main-1.0-SNAPSHOT");
        actualContent = "coal";
        actualHarbor = "Stockholm";
        actualDestination = "Helsingfors";

        DatabaseWrapper.clearAllLoads();
    }

    @Given("^a load with content coal$")
    public void registerLoad() throws Exception{
        SignIn.signIn(driver);

        WebElement element = driver.findElement(By.linkText("Administrate Loads"));
        element.click();

        //SUT
        RegisterLoad.registerLoad(driver, actualContent, actualHarbor, actualDestination);
    }

    @When("^you reserve it$")
    public void reserveLoad(){
        WebElement element = driver.findElement(By.linkText("Show Loads"));
        element.click();
        ReserveLoad.reserveLoad(actualContent,driver);
        element = driver.findElement(By.linkText("Administrate Loads"));
        element.click();
    }

    @Then("^the load will be reserved for you$")
    public void loadShouldBeReserved(){
        AssertTextWasFound.assertTextWasFound(driver, actualContent, actualHarbor, actualDestination);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
