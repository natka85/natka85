import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class FirstTest {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d\n";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
           }
    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get(baseUrl);
        WebElement inputCardNumber = driver.findElement(By.id("input-card-number"));
        assertEquals(driver.findElement(By.id("order-number")).getText(), "458211");
        inputCardNumber.click();
        inputCardNumber.clear();
        inputCardNumber.sendKeys("123");

        driver.findElement(By.xpath("//*[@id=\"card-number-field\"]/label")).click();

            assertEquals(driver.findElement(By.xpath("//div[@id='card-number-field']/div/label")).getText(), "Card number is not valid");

    }

    @Test
    public void testPaymentConfirmed() throws Exception {
        driver.get(baseUrl);
        WebElement inputCardNumber = driver.findElement(By.id("input-card-number"));
        WebElement inputCardHolder = driver.findElement(By.id("input-card-holder"));
        Select month = new Select(driver.findElement(By.id("card-expires-month")));
        Select year = new Select(driver.findElement(By.id("card-expires-year")));
        WebElement inputCardCvc = driver.findElement(By.id("input-card-cvc"));
        inputCardNumber.sendKeys("4000000000000002");
        inputCardHolder.sendKeys("Mikhail Shish");
        month.selectByValue("11");
        year.selectByIndex(10);
        inputCardCvc.sendKeys("777");
        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.submit();
        WebElement successButton = driver.findElement(By.id("success"));
        successButton.click();
               assertEquals(driver.findElement(By.xpath("//*[@id=\"payment-item-ordernumber\"]/div[2]")).getText(), "458211");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"payment-item-total-amount\"]")).getText(), "291.86");
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}


