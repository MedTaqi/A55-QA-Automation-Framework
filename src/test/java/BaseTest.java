import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
 public static WebDriver driver = null;
    public static WebDriverWait wait = null;
    public static Actions actions;

    ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
     public WebDriver getDriver(){
         return threadDriver.get();
     }

    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void openBrowser(@Optional String BaseUrl) throws MalformedURLException {
         threadDriver.set(pickBrowser(System.getProperty("browser")));
        threadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        threadDriver.get().manage().window().maximize();
         actions = new Actions(getDriver());
        getDriver().get(BaseUrl);

    }
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl="";
         switch (browser){
             case "firefox":
                 WebDriverManager.firefoxdriver().setup();
                 return driver= new FirefoxDriver();
             case "safari":
                 WebDriverManager.safaridriver().setup();
                 return driver= new SafariDriver();
             case "MicrosoftEdge":
                 WebDriverManager.edgedriver().setup();
                 return driver= new EdgeDriver();
             case "firefox-grid":
                 caps.setCapability("BrowserName","firefox");
                 return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
             default:
                 WebDriverManager.chromedriver();
                 ChromeOptions ops = new ChromeOptions();
                 ops.addArguments("--remote-allow-origins=*");
                 return driver = new ChromeDriver(ops);
         }

    }
    @AfterMethod
    public void closeBrowser(){
         threadDriver.get().close();
         threadDriver.remove();

    }
}