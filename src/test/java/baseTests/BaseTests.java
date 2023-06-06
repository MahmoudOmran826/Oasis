package baseTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class BaseTests {

    private WebDriver driver;
    private WebDriverWait wait;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected String userName = "testrcm";
    protected String password = "testrcm";
    protected String searchWord = "Manage Approvals";
    ChromeOptions options = new ChromeOptions();
    protected String expectedNumberOfPosts = "74";

    @BeforeTest
    public void setup() {

        WebDriverManager.chromedriver().setup();
        // ChromeDriver is just AWFUL because every version or two it breaks unless you pass cryptic arguments
//AGRESSIVE: options.setPageLoadStrategy(PageLoadStrategy.NONE); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
//        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
//        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
//        options.addArguments("--headless"); // only if you are ACTUALLY running headless
//        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
//        options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
//        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
//        options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        driver = new ChromeDriver(options);

//This option was deprecated, see https://sqa.stackexchange.com/questions/32444/how-to-disable-infobar-from-chrome
//options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        driver = new ChromeDriver();
//        WebDriverManager.firefoxdriver().setup();
//        driver=new FirefoxDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.get("https://testplus.oasisapp.services/dhbhp/faces/Home");

    }


    @AfterTest
    public void tearDown() {

        driver.quit();
    }

    public enum Months {
        January,
        February
    }

}
