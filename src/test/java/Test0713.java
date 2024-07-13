import Pages.LoginPage;
import org.testng.annotations.Test;

public class Test0713 extends BaseTest{
    @Test(priority = 0)

    public void loginValidCredentials(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.logIn();
    }
}
