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
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    private final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return threadDriver.get();
    }
    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(@Optional String BaseUrl) throws MalformedURLException {
    threadDriver.set(pickBrowser(System.getProperty("browser")));
    threadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    threadDriver.get().manage().window().maximize();
    threadDriver.get().manage().deleteAllCookies();
    wait = new WebDriverWait(getDriver(),Duration.ofSeconds(20));
    actions = new Actions(getDriver());
    PageFactory.initElements(driver,this);
    getDriver().get(BaseUrl);
    }

   public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl = "http://192.168.1.150:4444/";
        switch (browser){
            case "firefox":
            WebDriverManager.firefoxdriver().setup();
            return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                return driver = new EdgeDriver();
            case "safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();
            case"grid-firefox" :
                caps.setCapability("browserName", "firefox" );
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
            case"grid-Edge" :
                caps.setCapability("browserName", "MicrosoftEdge" );
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
            case"grid-Safari" :
                caps.setCapability("browserName", "safari" );
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
            case"grid-chrome" :
                caps.setCapability("browserName", "chrome" );
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
            case "cloud" :
                return lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--Remote-allow-origins=*");
                ops.addArguments("--disable-notifications");
                return driver= new ChromeDriver(ops);
        }
    }

    public WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("126");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "taqimed99");
        ltOptions.put("accessKey", "4pgzKgaVZzOS73tKVr8OmRqfKbWP14B21ArvrmZlll7yLKrab3");
        ltOptions.put("project", "Untitled");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);
        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }

    @AfterMethod
    public void tearDownBrowser() {
        threadDriver.get().close();
        threadDriver.remove();
    }
}