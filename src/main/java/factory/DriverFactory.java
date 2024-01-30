package factory;

import exceptions.BrowserNotFoundException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private String browserName = System.getProperty("browser.name","chrome");

    public WebDriver create(String mode){
        browserName = browserName.trim().toLowerCase();
        switch (browserName){
            case "chrome":{
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                options.addArguments(mode);
                return new ChromeDriver(options);
            }
        }
        throw new BrowserNotFoundException(browserName);
    }

    public WebDriver create(){
        browserName = browserName.trim().toLowerCase();
        switch (browserName){
            case "chrome":{
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        }
        throw new BrowserNotFoundException(browserName);
    }

}
