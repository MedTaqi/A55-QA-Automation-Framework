import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest {
    String DeleteMessage = "Deleted playlist vivo.";

    @Test
    public void deletePlaylist() throws InterruptedException {
        String DeleteMessage = "Deleted playlist \"vivo.\"";
        //Given
        provideEmail("taqimed99@gmail.com");
        providePassword("Med-20115-010499@");
        clickBtn();
        //WHEN
        //open playlist
        choosePlaylist();
        //delete playlist
        clickDeleteButton();
        //Then
        Thread.sleep(5000);
        Assert.assertEquals(DeleteMsg(), DeleteMessage);
    }

    public String DeleteMsg() {
        //WebElement msg = driver.findElement(By.cssSelector("div.success.show"));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return msg.getText();
    }

    private void clickDeleteButton() {
        //WebElement DeleteBtn = driver.findElement(By.cssSelector("[class='del btn-delete-playlist']"));
        WebElement DeleteBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='del btn-delete-playlist']")));
        DeleteBtn.click();
    }

    private void choosePlaylist() {
        //WebElement playlist = driver.findElement(By.xpath("//a[contains(text(),'vivo')]"));
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'vivo')]")));
        playlist.click();
    }
}
