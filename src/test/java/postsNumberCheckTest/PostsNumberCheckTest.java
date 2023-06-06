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
            .selectMonth(String.valueOf(Months.January))
            .clickOnDecrementYearsArrow(1)
            .selectFirstDayInTheCalender()
            .getNumberOfPosts();

        Assert.assertEquals(actualNumberOfPosts,expectedNumberOfPosts);
    }
}
