package cucmberAcceptanceTests;


import acceptanceTestHelper.RegisterLoad;
import acceptanceTestHelper.ReserveLoad;
import acceptanceTestHelper.SignIn;
import asserters.AssertTextWasFound;
import cucumber.annotation.After;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import databaseWrapper.DatabaseWrapper;
import cucumber.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URISyntaxException;
import java.sql.SQLException;

public class RegisterLoadSteps {
    private WebDriver driver;
    String actualContent;
    String actualHarbor;
    String actualDestination;

    @Before
    public void init() throws SQLException, URISyntaxException {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8080/LoadPlannerMain-1.0-SNAPSHOT");
        actualContent = "coal";
        actualHarbor = "Stockholm";
        actualDestination = "Helsingfors";

        DatabaseWrapper.clearAllLoads();
    }

    @Given("^you are logged in$")
    public void logIn(){
        SignIn.signIn(driver);
    }

    @When("^you register a load$")
    public void reserveLoad(){
        WebElement element = driver.findElement(By.linkText("Administrate Loads"));
        element.click();

        //SUT
        RegisterLoad.registerLoad(driver, actualContent, actualHarbor, actualDestination);
    }

    @Then("^the load will be registered$")
    public void loadShouldBeReserved(){
        WebElement element = driver.findElement(By.linkText("Show Loads"));
        element.click();

        AssertTextWasFound.assertTextWasFound(driver,actualContent,actualHarbor,actualDestination);

    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
