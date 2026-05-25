package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InscriptionPage {

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public InscriptionPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openPage() {
        driver.navigate().to("https://www.campusfrance.org/fr/user/register");
        try {
            Thread.sleep(2000);
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.elementToBeClickable(By.id("tarteaucitronPersonalize2")));
            driver.findElement(By.id("tarteaucitronPersonalize2")).click();
            js.executeScript("var el = document.getElementById('tarteaucitronAlertBig'); if(el) el.remove();");
            Thread.sleep(1000);
        } catch (Exception e) { }
    }

    private void fillCommonFields(Client client) throws Exception {
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#user-form #edit-name")));
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'}); arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input',{bubbles:true}));",
                emailField, client.email);

        driver.findElement(By.id("edit-pass-pass1")).sendKeys(client.password);
        driver.findElement(By.id("edit-pass-pass2")).sendKeys(client.confirmPassword);

        String civilityId = "Mr".equals(client.civility) ? "edit-field-civilite-mr" : "edit-field-civilite-mme";
        js.executeScript("arguments[0].click();", driver.findElement(By.id(civilityId)));

        driver.findElement(By.id("edit-field-nom-0-value")).sendKeys(client.lastName);
        driver.findElement(By.id("edit-field-prenom-0-value")).sendKeys(client.firstName);

        selectFromSelectize("edit-field-pays-concernes-wrapper", client.countryOfResidenceValue);

        driver.findElement(By.id("edit-field-nationalite-0-target-id")).sendKeys(client.nationality);
        driver.findElement(By.id("edit-field-code-postal-0-value")).sendKeys(client.postCode);
        driver.findElement(By.id("edit-field-ville-0-value")).sendKeys(client.city);
        driver.findElement(By.id("edit-field-telephone-0-value")).sendKeys(client.phone);
    }

    private void selectFromSelectize(String wrapperId, String dataValue) throws Exception {
        WebElement div = driver.findElement(By.cssSelector("#" + wrapperId + " .selectize-input"));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", div);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", div);
        Thread.sleep(500);
        WebElement opt = driver.findElement(By.cssSelector(".selectize-dropdown-content [data-value='" + dataValue + "']"));
        js.executeScript("arguments[0].click();", opt);
    }

    private void clickRadio(String id) throws Exception {
        WebElement btn = driver.findElement(By.id(id));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", btn);
    }

    private void accept() {
        driver.findElement(By.xpath("//*[@id=\"edit-field-accepte-communications-wrapper\"]/div")).click();
    }

    public void fillStudentForm() throws Exception {
        Client client = ClientReader.readFromJson("student.json");
        fillCommonFields(client);
        clickRadio("edit-field-publics-cibles-2");
        selectFromSelectize("edit-field-domaine-etudes-wrapper", client.studyFieldValue);
        selectFromSelectize("edit-field-niveaux-etude-wrapper", client.studyLevelValue);
        accept();
    }

    public void fillSearcherForm() throws Exception {
        Client client = ClientReader.readFromJson("searcher.json");
        fillCommonFields(client);
        clickRadio("edit-field-publics-cibles-3");
        selectFromSelectize("edit-field-domaine-etudes-wrapper", client.studyFieldValue);
        selectFromSelectize("edit-field-niveaux-etude-wrapper", client.studyLevelValue);
        accept();
    }

    public void fillInstitutionnelForm() throws Exception {
        Client client = ClientReader.readFromJson("admin.json");
        fillCommonFields(client);
        clickRadio("edit-field-publics-cibles-4");
        driver.findElement(By.id("edit-field-fonction-0-value")).sendKeys(client.function);
        selectFromSelectize("edit-field-type-organisme-wrapper", client.organizationTypeValue);
        driver.findElement(By.id("edit-field-nom-organisme-0-value")).sendKeys(client.organizationName);
        accept();
    }
}