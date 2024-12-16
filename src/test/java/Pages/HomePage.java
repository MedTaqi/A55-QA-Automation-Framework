package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    @FindBy(css = "[class='avatar']")
    private WebElement loginSuccess;
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    public WebElement loginSuccess(){
        return wait.until(ExpectedConditions.visibilityOf(loginSuccess));
    }
}
