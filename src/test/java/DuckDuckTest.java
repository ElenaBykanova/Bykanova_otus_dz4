import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DuckDuckTest {
    private WebDriver driver;
    private final Logger logger = LogManager.getLogger(DuckDuckTest.class);
    private String url = "https://duckduckgo.com/";


    @BeforeEach
    public void startDriver() {
        driver = new DriverFactory().create("headless");
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
    public void findOtusOnDuckDuck() {
        driver.get(url);
        logger.info("Зашли на сайт duckduckgo.com в headless режиме");

        WebElement elSerchLine = driver.findElement(By.xpath("//input[@id='searchbox_input']"));
        elSerchLine.click();
        elSerchLine.sendKeys("Отус" + Keys.ENTER);
        logger.info("Выполнен поиск по слову Отус");

        WebElement elFirstResult = driver.findElement(By.xpath(
                "//article[@data-testid='result'][1]//span[contains(text()," +
                        "'Онлайн‑курсы для профессионалов, дистанционное обучение')]"));

        String text = elFirstResult.getText();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...",text,
                "First result is not OTUS");
        logger.info("Выполнена проверка на результат поиска, Отус первый в выдаче результатов");

    }

}
