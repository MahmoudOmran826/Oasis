package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
//Variables
private WebDriver driver;
private WebDriverWait wait;
//    Locators
//    //div[@class='form-group']
// //input[contains(@id, 'username') and contains(@name, 'username')][@placeholder='Email']

    private By userName =By.name("it1");
    private By password =By.name("it2");
    private By loginBtn =By.id("login");

//    Methods
    public LoginPage enterUserName(String userNameString){
//        wait.until(ExpectedConditions.presenceOfElementLocated(userName));
        driver.findElement(userName).clear();
        driver.findElement(userName).sendKeys(userNameString);
        return new LoginPage(driver);
    }

    public LoginPage enterPassword(String passwordString){
        wait.until(ExpectedConditions.presenceOfElementLocated(password));
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(passwordString);
        return new LoginPage(driver);
    }
    public HomePage clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        driver.findElement(loginBtn).click();
        return new HomePage(driver);
    }
}
