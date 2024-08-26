package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver givenDriver){
      driver = givenDriver;
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      actions = new Actions(driver);
        PageFactory.initElements(driver,this);

    }
    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement findElement(WebElement element){
       return wait.until(ExpectedConditions.visibilityOf(element);
    }
    public void doubleClick(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        actions.doubleClick(findElement(element)).perform();
    }
}
