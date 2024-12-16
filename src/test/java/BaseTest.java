import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

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
   /* public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl="http://192.168.1.150:4444";
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
             case "grid-chrome":
                 caps.setCapability("BrowserName","chrome");
                 return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
             default:
                 WebDriverManager.chromedriver();
                 ChromeOptions ops = new ChromeOptions();
                 ops.addArguments("--remote-allow-origins=*");
                 return driver = new ChromeDriver(ops);
         }
*/
   public WebDriver pickBrowser(String browser) throws MalformedURLException {
       DesiredCapabilities caps = new DesiredCapabilities();
       String gridUrl = "http://192.168.1.150:4444/";
       switch (browser) {
           case "firefox":
               WebDriverManager.firefoxdriver().setup();
               FirefoxOptions ops = new FirefoxOptions();
               ops.addArguments("-private");
               return driver = new FirefoxDriver(ops);
           case "MicrosoftEdge":
               WebDriverManager.edgedriver().setup();
               EdgeOptions EdgOps = new EdgeOptions();
               EdgOps.addArguments("--remote-allow-origins=*");
               return driver = new EdgeDriver(EdgOps);
           case "grid-firefox":
               caps.setCapability("browserName", "firefox");
               return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
           case "grid-edge":
               caps.setCapability("browserName", "MicrosoftEdge");
               return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
           case "grid-chrome":
               caps.setCapability("browserName", "chrome");
               return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
           case "cloud":
               return lambdaTest();
           default:
               WebDriverManager.chromedriver().setup();
               ChromeOptions options = new ChromeOptions();
               options.addArguments("--remote-allow-origins=*");
               options.addArguments("--disable-notifications");
               return driver = new ChromeDriver(options);

       }
   }

    public WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("131");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "taqimed99");
        ltOptions.put("accessKey", "4pgzKgaVZzOS73tKVr8OmRqfKbWP14B21ArvrmZlll7yLKrab3");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }

    @AfterMethod
    public void closeBrowser(){
         threadDriver.get().close();
         threadDriver.remove();

    }
}