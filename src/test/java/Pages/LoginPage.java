package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(css = "[type='email']")
    private WebElement emailField;
    @FindBy(css = "[type='password']")
    private WebElement passwordField;
    @FindBy(css = "[type='submit']")
    private WebElement clickBtn;


    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    public LoginPage provideEmail(String email){
        emailField.sendKeys(email);
        return this;
    }
    public LoginPage providePassword(String password){
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPage click(){
        clickBtn.click();
        return this;
    }
    public LoginPage logIn(){
        provideEmail("taqimed99@gmail.com");
        providePassword("Med-20115-010499@");
        click();
        return this;
    }
}
