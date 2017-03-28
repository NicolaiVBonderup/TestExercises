
import com.jayway.restassured.RestAssured;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author bepis
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {

    private WebDriver driver;

    public SeleniumTest() {
    }

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bepis\\Documents\\SeleniumEx1\\driver\\chromedriver.exe");

        RestAssured.given().get("http://localhost:3000/reset");

        driver = new ChromeDriver();
        driver.get("localhost:3000");

    }

    @After
    public void tearDown() {

        driver.quit();
        RestAssured.given().get("http://localhost:3000/reset");

    }

    @Test
    public void testCars1() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                int tableSize = getTableSize(driver);

                assertThat(tableSize, is(5));

                return true;
            }
        });
    }

    @Test
    public void testCars2() {

        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                driver.findElement(By.id("filter")).sendKeys("2002");

                int tableSize = getTableSize(driver);

                assertThat(tableSize, is(2));

                return true;
            }
        });

    }

    @Test
    public void testCars3() {

        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {

                driver.findElement(By.id("filter")).sendKeys("hurrdurr");
                int tableSize = getTableSize(driver);
                assertThat(tableSize, is(0));

                driver.findElement(By.id("filter")).clear();
                tableSize = getTableSize(driver);
                // Just using .clear() is not enough to make the DOM reload.
                assertThat(tableSize, is(0));

                driver.findElement(By.id("filter")).sendKeys(" ");
                tableSize = getTableSize(driver);
                assertThat(tableSize, is(5));

                return true;
            }
        });

    }

    @Test
    public void testCars4() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {

                driver.findElement(By.id("h_year")).click();

                List<WebElement> trs = driver.findElement(By.id("tbodycars")).findElements(By.tagName("tr"));

                String id938 = trs.get(0)
                        .findElements(By.tagName("td"))
                        .get(0)
                        .getText();

                assertThat(id938, is("938"));

                String id940 = trs.get(trs.size() - 1)
                        .findElements(By.tagName("td"))
                        .get(0)
                        .getText();

                assertThat(id940, is("940"));

                return true;
            }
        });
    }

    @Test
    public void testCars5() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {

                List<WebElement> trs = driver.findElement(By.id("tbodycars")).findElements(By.tagName("tr"));

                WebElement tr = getCarRow(trs, "938");

                //WebElement id938 = driver.findElement(By.id("tbodycars")).findElement(By.xpath("td[contains(text(),'938')]/following-sibling::tr[contains(@class,'value')]"));
                List<WebElement> tds = tr.findElements(By.tagName("td"));

                tds.get(tds.size() - 1)
                        .findElements(By.tagName("a"))
                        .get(0)
                        .click();

                WebElement descriptionField = driver.findElement(By.id("description"));
                descriptionField.clear();
                descriptionField.sendKeys("Cool car");
                driver.findElement(By.id("save")).click();
                
                waitUntilPageRefreshed("Cool car");

                assertThat(tds.get(5).getText(), is("Cool car"));

                return true;
            }
        });
    }

    @Test
    public void testCars6() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                driver.findElement(By.id("new")).click();
                driver.findElement(By.id("save")).click();

                String error = driver.findElement(By.id("submiterr")).getText();
                assertThat(error, is("All fields are required"));

                assertThat(getTableSize(driver), is(5));

                return true;
            }
        });

    }

    @Test
    public void testCars7() {

        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                driver.findElement(By.id("new")).click();

                driver.findElement(By.id("year")).sendKeys("2008");
                driver.findElement(By.id("registered")).sendKeys("2002-5-5");
                driver.findElement(By.id("make")).sendKeys("Kia");
                driver.findElement(By.id("model")).sendKeys("Rio");
                driver.findElement(By.id("description")).sendKeys("As new");
                driver.findElement(By.id("price")).sendKeys("31000");

                driver.findElement(By.id("save")).click();

                //WebElement wait = (new WebDriverWait(driver, 10))
                //        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Rio')]")));
                
                waitUntilPageRefreshed("Rio");

                WebElement tr = getCarRow(driver.findElement(By.id("tbodycars")).findElements(By.tagName("tr")), "942");
                assertThat(getTableSize(driver), is(6));
                assertThat(tr, notNullValue());

                return true;
            }
        });

    }

    public int getTableSize(WebDriver drive) {
        return drive.findElement(By.id("tbodycars"))
                .findElements(By.tagName("tr"))
                .size();
    }

    public WebElement getCarRow(List<WebElement> rows, String carId) {

        for (WebElement tr : rows) {

            if (carId.equals(tr.findElements(By.tagName("td")).get(0).getText())) {

                return tr;
            }
        }
        return null;
    }
    
    public void waitUntilPageRefreshed(String elementToWatch) {
        
        WebElement wait = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '" + elementToWatch + "')]")));
        
    }
}
