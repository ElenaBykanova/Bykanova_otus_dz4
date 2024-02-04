import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LayoutsTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private final Logger logger = LogManager.getLogger(LayoutsTest.class);

    @BeforeEach
    public void startDriver() {
        driver = new DriverFactory().create("-start-fullscreen");
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
    public void layoutsClickOnImg(){

        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        logger.info("Открыт сайт demo.w3layouts в режиме киоска");


        List<WebElement> listOfImg = driver.findElements(By.cssSelector("div.content-overlay"));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView()", listOfImg.get(0));

        listOfImg.get(0).click();
        logger.info("Кликнули по картинке");

        String modalSelector = ".pp_hoverContainer";

        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(modalSelector))));

        logger.info("Дождались открытия картинки в модальном окне");

    }

}
