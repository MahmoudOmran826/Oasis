package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    private By dropDownYearDecrement = By.xpath("//a[@id='pt1:contrRg:0:CntRgn:2:pt1:pt_or1:pt_oc2:pt_or7:pt_oc11:oc22:or11:oc16:oc1:or7:oc68:ff13:fi25:id13::pop::cd::ys::decrement']");

//    xpath : (//select[@class='xos'])[3]
    private By monthsDropDownMenu = By.xpath("(//select[@class='xos'])[3]");
    private By firstDayInCalender = By.xpath("(//tr[@class='xoc p_AFFirst'][@role='row'])[3]//td[text()='1']");
    private By postedPosts = By.cssSelector("tbody tr td span.x3nz.x3o7.x3va");
    private By formDateTextField = By.id("pt1:contrRg:0:CntRgn:2:pt1:pt_or1:pt_oc2:pt_or7:pt_oc11:oc22:or11:oc16:oc1:or7:oc68:ff13:fi25:id13::content");
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
    public HomePage selectCalenderIcon() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(selectDate));
        wait.until(ExpectedConditions.elementToBeClickable(selectDate));

        driver.findElement(selectDate).click();
        Thread.sleep(5000);

        return new HomePage(driver);
    }

    public HomePage clickOnDecrementYearsArrow(int numberOfClicks ) throws InterruptedException {
        if (numberOfClicks<=0){
            numberOfClicks = 1;
        }

        for (int clicks =0 ;clicks<numberOfClicks;clicks++){
            int attempts = 0;
            while(attempts < 4) {
                try {
//                    wait.until(ExpectedConditions.presenceOfElementLocated(dropDownYearDecrement));
//                    Thread.sleep(2000);
                    wait.until(ExpectedConditions.elementToBeClickable(dropDownYearDecrement));
                    driver.findElement(dropDownYearDecrement).click();


                    break;
                } catch(Exception e) {
                    Thread.sleep(3000);
                    driver.findElement(selectDate).click();
                    driver.findElement(dropDownYearDecrement).click();
                    e.printStackTrace();
                }
                attempts++;
            }
       }
        return new HomePage(driver);
    }

    public HomePage selectMonth(String month ) throws InterruptedException {
        int attempts = 0;
        while(attempts < 2) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(monthsDropDownMenu));
                monthsDropDown = new Select(driver.findElement(By.xpath("(//select[@class='xos'])[3]")));
                monthsDropDown.selectByVisibleText("January");
//                driver.findElement(monthsDropDownMenu).click();
//                driver.findElement(By.xpath("//select[@id='pt1:contrRg:0:CntRgn:2:pt1:pt_or1:pt_oc2:pt_or11:pt_oc8:qryId1:val30::pop::dlg::cd::mSel::content']//option[@value='0']")).click();



                break;
            } catch(Exception e) {
                Thread.sleep(5000);

                driver.findElement(selectDate).click();
                wait.until(ExpectedConditions.elementToBeClickable(monthsDropDownMenu));
                monthsDropDown = new Select(driver.findElement(By.xpath("(//select[@class='xos'])[3]")));
                monthsDropDown.selectByVisibleText("January");
            }
            attempts++;
        }

        return new HomePage(driver);
    }

    public HomePage selectFirstDayInTheCalender() {
        int attempts = 0;
        while(attempts < 2) {
            try {
                wait.until(ExpectedConditions.attributeContains(By.xpath("//input[@title='2022']"),"title","2022"));
                wait.until(ExpectedConditions.elementToBeClickable( firstDayInCalender));
                Thread.sleep(3000);
                driver.findElement(firstDayInCalender).click();
                Thread.sleep(3000);
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@title='01-01/2022']"))));
                break;
            } catch(Exception e) {
            }
            attempts++;
        }

//        Thread.sleep(10000);
//        driver.findElement(firstDayInCalender).sendKeys(Keys.ENTER);

        return new HomePage(driver);
    }

    public String getNumberOfPosts() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(postedPosts));
       String postedNumber= driver.findElement(postedPosts).getText();
        System.out.println(postedNumber);
        return postedNumber;
    }

    public HomePage setFormDateTextField(String dd_mm_yyyy) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(formDateTextField));
//        driver.findElement(formDateTextField).click();
//        Thread.sleep(5000);
        driver.findElement(formDateTextField).sendKeys(dd_mm_yyyy);
//        Thread.sleep(5000);
        driver.findElement(formDateTextField).sendKeys(Keys.TAB);
//        Thread.sleep(5000);
        return new HomePage(driver);
    }
}
