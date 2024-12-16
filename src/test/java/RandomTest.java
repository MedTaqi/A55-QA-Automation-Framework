import Pages.HomePage;
import Pages.LoginPage;
import com.sun.source.tree.AssertTree;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Handler;

public class RandomTest extends BaseTest{
    @Test

    public void LogIn(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.enterEmail("taqimed99@gmail.com");
        loginPage.enterPassword("Med-20115-010499@");
        loginPage.clickSubmit();
        Assert.assertTrue(homePage.loginSuccess().isDisplayed());
    }
}
