package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    @FindBy(css = "[type='email']")
    private WebElement emailField;
    @FindBy(css = "[type='password']")
    private WebElement passwordField;
    @FindBy(css = "[type='submit']")
    private WebElement click;
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    public LoginPage enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        return this;
    }
    public LoginPage enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        return this;
    }
    public LoginPage clickSubmit(){
        wait.until(ExpectedConditions.elementToBeClickable(click)).click();
        return this;
    }
}
