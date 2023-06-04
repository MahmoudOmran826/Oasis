package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    //Variables
    private WebDriver driver;
    private WebDriverWait wait;
    private Select monthsDropDown;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
//    Locators
//    css Selector : .hamburger-menu-btn
//    xpath : //span[contains(@id,'hamburgerBtn')]
    private By hamburgerMenuBtn = By.cssSelector(".hamburger-menu-btn");
//    css selector : div.menuSearchWrapper  oj-input-text.oj-complete input
    private By searchBox = By.cssSelector("div.menuSearchWrapper  oj-input-text.oj-complete input");
    private By firstElementInTheTreeView = By.cssSelector(".oj-treeview-item-text");
    private By selectDate = By.xpath("//a[@title='Select Date']");
    private By dropDownYearDecrement = By.xpath("(//tbody/tr/td//div[@class='xol']/a[@role='button'])[9]");

//    xpath : (//select[@class='xos'])[3]
    private By monthsDropDownMenu = By.xpath("(//select[@class='xos'])[3]");
    private By firstDayInCalender = By.xpath("(//tr[@class='xoc p_AFFirst'][@role='row'])[3]//td[text()='1']");
    private By postedPosts = By.cssSelector("tbody tr td span.x3nz.x3o7.x3va");
//    Methods
    public HomePage clickHamburgerMenuBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenuBtn));
        driver.findElement(hamburgerMenuBtn).click();
        return new HomePage(driver);
    }
    public HomePage enterTextInTheSearchBox(String searchText){
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(searchText);
        return new HomePage(driver);
    }
    public HomePage selectFirstElementInTheTreeResults(){
        wait.until(ExpectedConditions.elementToBeClickable(firstElementInTheTreeView));
        driver.findElement(firstElementInTheTreeView).click();
        return new HomePage(driver);
    }
    public HomePage selectCalenderIcon(){
        wait.until(ExpectedConditions.presenceOfElementLocated(selectDate));
        wait.until(ExpectedConditions.elementToBeClickable(selectDate));

        driver.findElement(selectDate).click();
        return new HomePage(driver);
    }

    public HomePage clickOnDecrementYearsArrow(int numberOfClicks ){
        if (numberOfClicks<=0){
            numberOfClicks = 1;
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(dropDownYearDecrement));
        wait.until(ExpectedConditions.elementToBeClickable(dropDownYearDecrement));
        for (int clicks =0 ;clicks<numberOfClicks;clicks++){
        driver.findElement(dropDownYearDecrement).click();}
        return new HomePage(driver);
    }

    public HomePage selectMonth(String month ) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(monthsDropDownMenu));
        monthsDropDown = new Select(driver.findElement(By.xpath("(//select[@class='xos'])[3]")));
        monthsDropDown.selectByVisibleText("January");
        Thread.sleep(4000);
        return new HomePage(driver);
    }

    public HomePage selectFirstDayInTheCalender()  {
        wait.until(ExpectedConditions.elementToBeClickable( firstDayInCalender));
        driver.findElement(firstDayInCalender).click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@title='01-01/2022']"))));

        return new HomePage(driver);
    }

    public String getNumberOfPosts() throws InterruptedException {
        Thread.sleep(4000);
        wait.until(ExpectedConditions.presenceOfElementLocated(postedPosts));
        return driver.findElement(postedPosts).getText();
    }

}
