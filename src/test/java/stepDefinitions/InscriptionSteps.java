package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.InscriptionPage;
public class InscriptionSteps {

    private WebDriver driver;
    private InscriptionPage page;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new InscriptionPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Given("I am on the {string} page")
    public void iAmOnThePage(String pageName) {
        page.openPage();
    }

    @Given("I fill the registration form as a student")
    public void fillStudentForm() throws Exception {
        page.fillStudentForm();
    }

    @Given("I fill the registration form as a searcher")
    public void fillSearcherForm() throws Exception {
        page.fillSearcherForm();
    }

    @Given("I fill the registration form as an institutional user")
    public void fillInstitutionnelForm() throws Exception {
        page.fillInstitutionnelForm();
    }

    @When("I verify the registration submit button is available")
    public void verifySubmitButton() {
        WebElement submitBtn = driver.findElement(By.cssSelector("#user-form #edit-submit"));
        Assertions.assertTrue(submitBtn.isDisplayed());
    }

    @Then("the submit button should display {string}")
    public void submitButtonDisplays(String expectedText) {
        WebElement submitBtn = driver.findElement(By.cssSelector("#user-form #edit-submit"));
        Assertions.assertTrue(submitBtn.getAttribute("value").contains("un compte"));
    }
}