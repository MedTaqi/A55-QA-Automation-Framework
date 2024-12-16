package LoginStepDefinition;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import Pages.LoginPage;
import Pages.HomePage;
public class StepDefinition {
    WebDriver driver;
    WebDriverWait wait;
    WebElement emailField = driver.findElement(By.cssSelector( "[type='email']"));
    WebElement passwordField = driver.findElement(By.cssSelector( "[type='email']"));
    WebElement click =driver.findElement(By.cssSelector( "[type='email']"));
    WebElement loginSuccess = driver.findElement(By.cssSelector( "[type='email']"));


    @Before
    @Given("I open Browser")
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--remote-allow-origins=*");
        opts.addArguments("--disable-notifications");
        driver = new ChromeDriver(opts);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @And("I open Login page")
    public void iOpenLoginPage() {
        driver.get("https://qa.koel.app/");
    }
    @When("I enter email{string}")
    public void iEnterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);

    }
    @And("I enter password{string}")
    public void iEnterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);

    }

    @Then("I am logged in")
    public WebElement iAmLoggedIn() {
        return wait.until(ExpectedConditions.visibilityOf(loginSuccess));

    }

    @And("I click submit")
    public void iClickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(click)).click();

    }
}
