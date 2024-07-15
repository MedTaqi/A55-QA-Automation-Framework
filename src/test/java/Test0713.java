import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test0713 extends BaseTest{
    String notification1= "Created playlist \"Test1.\"";
    String notification2= "Updated playlist \"Test2.\"";
    String notification3= "Deleted playlist \"Test2.\"";
    @Test(priority = 0)
    public void loginValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.logIn();
        Assert.assertTrue(homePage.loggedIn().isDisplayed());
    }
    @Test(priority = 1)
    public void createPlaylist(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.logIn();
        Assert.assertTrue(homePage.loggedIn().isDisplayed());
        homePage.clickPlusBtn();
        homePage.chooseSimplePlaylist();
        homePage.enterPlaylistName();
        Assert.assertEquals(homePage.playlistCreated(),notification1);
    }
    @Test (priority = 2)
    public void renamePlaylist(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.logIn();
        Assert.assertTrue(homePage.loggedIn().isDisplayed());
        homePage.contextClickPlaylist();
        homePage.editPlaylist();
        homePage.addNewName();
        Assert.assertEquals(homePage.playlistUpdated(),notification2);
    }
    @Test (priority = 3)
    public void DeletePlaylist(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.logIn();
        Assert.assertTrue(homePage.loggedIn().isDisplayed());
        homePage.contextClickPlaylist1();
        homePage.editPlaylist();
        homePage.clickDelete();
        Assert.assertEquals(homePage.playlistDeleted(),notification3);
    }
}
