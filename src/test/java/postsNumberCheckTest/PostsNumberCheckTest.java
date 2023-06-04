package postsNumberCheckTest;

import baseTests.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostsNumberCheckTest extends BaseTests {
    @Test
    public void testNumberOfPosts() throws InterruptedException {
    String actualNumberOfPosts =loginPage.enterUserName(userName)
            .enterPassword(password)
            .clickLoginBtn()
            .clickHamburgerMenuBtn()
            .enterTextInTheSearchBox(searchWord)
            .selectFirstElementInTheTreeResults()
            .selectCalenderIcon()
            .clickOnDecrementYearsArrow(1)
            .selectMonth(String.valueOf(Months.January))
            .selectFirstDayInTheCalender()
            .getNumberOfPosts();
        Assert.assertEquals(actualNumberOfPosts,expectedNumberOfPosts);
    }
}
