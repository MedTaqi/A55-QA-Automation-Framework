import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
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
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;
    private static final ThreadLocal<WebDriver>threadDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    @BeforeMethod
    @Parameters({"baseUrl"})
    void launchBrowser(@Optional String baseUrl) throws MalformedURLException {
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        threadDriver.get().manage().window().maximize();
        threadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
        actions = new Actions(getDriver());
        threadDriver.get().manage().deleteAllCookies();
        PageFactory.initElements(driver,this);
        getDriver().get("baseUrl");
    }
    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl = "http://192.168.1.155:4444";
        switch (browser){
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fops = new FirefoxOptions();
                fops.addArguments("--private");
                return driver = new FirefoxDriver();
            case "Safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions eOps = new EdgeOptions();
                eOps.addArguments("--remoe-allow-origins=*");
                return driver = new EdgeDriver();
            case "grid-Firefox":
                caps.setCapability("browserName","Firefox");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "grid-Safari":
                caps.setCapability("browserName","Safari");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "grid-MicrosoftEdge":
                caps.setCapability("browserName","MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "grid-Chrome":
                caps.setCapability("browserName","Chrome");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "cloud":
                return LambdaTest();
            default :
                WebDriverManager.chromedriver().setup();
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--remote-allow-origins=*");
                return  driver = new ChromeDriver(ops);
        }

    }

    private static WebDriver LambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("138");
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
    public void tearDownBrowser(){
        threadDriver.get().close();
        threadDriver.remove();
    }
}