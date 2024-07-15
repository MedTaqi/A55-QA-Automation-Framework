package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.ref.PhantomReference;

public class HomePage extends BasePage{
    @FindBy(css = "[class='avatar']")
    private WebElement successLogin;
    @FindBy(css = "[class='fa fa-plus-circle create']")
    private WebElement plusBtn ;
    @FindBy(css = "[data-testid='playlist-context-menu-create-simple']")
    private WebElement simplePlaylist;
    @FindBy(css = "[name='name']")
    private WebElement playlistInputField;
    @FindBy(css = "[class ='success show']")
    private WebElement successPlaylist;
    @FindBy(css = "[class='success show']")
    private WebElement notif1;
    @FindBy(xpath = "//Section[@id='playlists']//li//a[contains(text(),'Test1')]")
    private WebElement firstPlaylist;
    @FindBy(xpath = "//Section[@id='playlists']//li//a[contains(text(),'Test2')]")
    private WebElement playlist2;
    @FindBy(xpath = "//*[@id='playlists']//ul//li[contains(text(),'Edit')]")
    private WebElement Edit;
    @FindBy(xpath = "//*[@id='playlists']//ul//li[contains(text(),'Delete')]")
    private WebElement Delete;

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    public WebElement loggedIn(){
        return successLogin;
    }
    public HomePage clickPlusBtn(){
        plusBtn.click();
        return this;
    }
    public HomePage chooseSimplePlaylist(){
        simplePlaylist.click();
        return this;
    }
    public HomePage enterPlaylistName(){
        playlistInputField.sendKeys("Test1");
        playlistInputField.sendKeys(Keys.ENTER);
        return this;
    }
    public String playlistCreated(){
        wait.until(ExpectedConditions.visibilityOf(notif1));
        return notif1.getText();
    }
    public HomePage contextClickPlaylist(){
        actions.contextClick(firstPlaylist).perform();
        return this;
    }
    public HomePage editPlaylist(){
    Edit.click();
    return this;
    }
    public HomePage addNewName(){
        playlistInputField.sendKeys(Keys.chord(Keys.COMMAND,"A",Keys.BACK_SPACE));
        playlistInputField.sendKeys("Test2");
        playlistInputField.sendKeys(Keys.ENTER);
        return this;
    }
    public String playlistUpdated(){
        wait.until(ExpectedConditions.visibilityOf(notif1));
        return notif1.getText();
    }
    public HomePage contextClickPlaylist1(){
        actions.contextClick(playlist2).perform();
        return this;
    }
    public HomePage clickDelete(){
        Delete.click();
        return this;
    }
    public String playlistDeleted(){
        wait.until(ExpectedConditions.visibilityOf(notif1));
        return notif1.getText();
    }


}
