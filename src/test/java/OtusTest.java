import factory.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OtusTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private final Logger logger = LogManager.getLogger(OtusTest.class);
    private String login = System.getProperty("login");
    private String password = System.getProperty("password");

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {
        driver = new DriverFactory().create();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Драйвер запущен");
    }

    @AfterEach
    public void stopDriver(){
        if (driver != null){
            driver.quit();
            logger.info("Драйвер остановлен");
        }
    }

    @Test
    public void otusAuthAndCookies(){
        driver.get("https://otus.ru");
        logger.info("Перешли на сайт otus.ru");
        String LoginButtonLocator = "//button[text()='Войти']";
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(LoginButtonLocator))));
        driver.findElement(By.xpath(LoginButtonLocator)).click();

        driver.findElement(By.xpath("//div[./input[@name='email']]")).click();
        WebElement elInputEmail = driver.findElement(By.xpath("//input[@name='email']"));
        elInputEmail.sendKeys(login);

        driver.findElement(By.xpath("//div[./input[@type='password']]")).click();
        WebElement elInputPassword = driver.findElement(By.xpath("//input[@type='password']"));
        elInputPassword.sendKeys(password);
        logger.info("Ввели логин и пароль");

        driver.findElement(By.cssSelector("#__PORTAL__ button")).click();
        logger.info("Вошли в личный кабинет");

        logger.info("Выводим все куки:" + driver.manage().getCookies());


    }

}
