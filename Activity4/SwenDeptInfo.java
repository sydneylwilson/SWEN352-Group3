import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SwenDeptInfo {
    private WebDriver driver;

    private String baseUrl;

    @BeforeEach
    void setUp() throws Exception {
        SwenDeptInfo.Browser browser = SwenDeptInfo.Browser.FIREFOX; // Can be changed to Browser.CHROME
        driver = browser.setUpDriver();
        baseUrl = "https://"; // TARGET URL
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void testClassSearchButton() throws Exception {
        driver.get(baseUrl+"tigercenter.rit.edu/tigerCenterApp/landing");
        WebElement classButton = driver.findElement(By.xpath("//*[@id=\"angularApp\"]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/landing-page/div/div/div/div/div[4]/a[1]"));
        assertEquals("Class Search", classButton.getText());
        classButton.click();
    }

    @Test
    /**
     * This is for Part 3.2
     * Software Engineering department
     */
    public void testNaveenSharma() throws Exception {
//        https://www.rit.edu/computing/contact
        driver.get("https://www.rit.edu/");
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[3]/header[2]/section[4]/div/div/div/a/span[1]"));
        searchButton.click();
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"ritSearch\"]"));
        searchBox.sendKeys("GCCIS");
        Thread.sleep(1000);
        WebElement searchButton2 = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div[2]/div[1]/div/form/div/div/div[2]/button"));
        searchButton2.click();
        searchButton2.click();
        Thread.sleep(1000);
        WebElement gccisLink = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div[2]/div[3]/div[1]/div[1]/div/div/div[3]/div/div/div/div/div[5]/div[2]/div/div/div[1]/div[1]/div/div[1]/div/a"));
        driver.get(gccisLink.getAttribute("href"));
        Thread.sleep(1000);
        WebElement contact = driver.findElement(By.xpath("/html/body/div[3]/header[2]/section[3]/nav/div/ul/li[7]/a"));
        contact.click();
        Thread.sleep(1000);
//        scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
        Thread.sleep(1000);
        WebElement softwareEngineering = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div/div/div[7]/div[1]/p/a"));
        softwareEngineering.click();
        String name = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div/div/div[7]/div[2]/div/div/article/div/div[2]/div[1]")).getText();
        String email = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div/div/div[7]/div[2]/div/div/article/div/div[3]/div[1]/a")).getText();
        String title = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div/div/div[7]/div[2]/div/div/article/div/div[2]/div[2]")).getText();
        String department = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div/div/div[7]/div[2]/div/div/article/div/div[2]/div[3]")).getText();
//        String college = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div/div/div[7]/div[2]/div/div/article/div/div[2]/div[4]")).getText();
        String address = driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div/div/div[1]/div/div/div/div/div/div/p[1]")).getText();
        System.out.println(name + "\n" + email + "\n" + title + "\n" + department + "\n" + address + "\n");
        Thread.sleep(5000);
    }

    private enum Browser {
        CHROME("webdriver.chrome.driver",
                "chromedriver",
                ChromeDriver::new),
        FIREFOX("webdriver.gecko.driver",
                "geckodriver",
                FirefoxDriver::new);

        private final String driverPropertyKey;
        private final String driverBaseName;
        private final Supplier<WebDriver> webDriverSupplier;

        Browser(String driverPropertyKey,
                String driverBaseName,
                Supplier<WebDriver> webDriverSupplier) {
            this.driverPropertyKey = driverPropertyKey;
            this.driverBaseName = driverBaseName;
            this.webDriverSupplier = webDriverSupplier;
        }

        private WebDriver setUpDriver() {
            String driverName = this.driverBaseName;
            if (System.getProperty("os.name").startsWith("Windows")) {
                driverName += ".exe";
            }
            System.setProperty(this.driverPropertyKey, driverName);
            return this.webDriverSupplier.get();
        }
    }
}

