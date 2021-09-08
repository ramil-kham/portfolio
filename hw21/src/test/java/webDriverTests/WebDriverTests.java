package webDriverTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverTests {
    WebDriver webDriver;

    public void chooseWebBrowser(String browserName) {
        switch (browserName) {
            case "opera":
                System.setProperty("webdriver.opera.driver", "C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw21\\src\\main\\resources\\operadriver.exe");
                webDriver = new OperaDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw21\\src\\main\\resources\\geckodriver.exe");
                webDriver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Airis\\IdeaProjects\\portfolio\\hw21\\src\\main\\resources\\chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
        }
    }

    @Test
    public void webDriverFirefoxTest() {
        chooseWebBrowser("firefox");
        webDriver.get("https://www.tanuki.ru/");
        Assertions.assertEquals("Доставка суши круглосуточно в Москве, заказ суши в ресторане Тануки", webDriver.getTitle());
        Assertions.assertEquals("https://www.tanuki.ru/", webDriver.getCurrentUrl());
        webDriver.quit();
    }

    @Test
    public void webDriverOperaTest() {
        chooseWebBrowser("opera");
        webDriver.get("https://www.tanuki.ru/");
        Assertions.assertEquals("Доставка суши круглосуточно в Москве, заказ суши в ресторане Тануки", webDriver.getTitle());
        Assertions.assertEquals("https://www.tanuki.ru/", webDriver.getCurrentUrl());
        webDriver.quit();
    }

    @Test
    public void webDriverChromeTest() {
        chooseWebBrowser("chrome");
        webDriver.get("https://www.tanuki.ru/");

        webDriver.manage().window().setSize(new Dimension(1440,1024));
        Assertions.assertEquals("Доставка суши круглосуточно в Москве, заказ суши в ресторане Тануки", webDriver.getTitle());
        Assertions.assertEquals("https://www.tanuki.ru/", webDriver.getCurrentUrl());

        webDriver.manage().window().setSize(new Dimension(1024,768));
        Assertions.assertEquals("Доставка суши круглосуточно в Москве, заказ суши в ресторане Тануки", webDriver.getTitle());
        Assertions.assertEquals("https://www.tanuki.ru/", webDriver.getCurrentUrl());

        webDriver.manage().window().setSize(new Dimension(650,900));
        Assertions.assertEquals("Доставка суши круглосуточно в Москве, заказ суши в ресторане Тануки", webDriver.getTitle());
        Assertions.assertEquals("https://www.tanuki.ru/", webDriver.getCurrentUrl());

        webDriver.manage().window().setSize(new Dimension(350,900));
        Assertions.assertEquals("Доставка суши круглосуточно в Москве, заказ суши в ресторане Тануки", webDriver.getTitle());
        Assertions.assertEquals("https://www.tanuki.ru/", webDriver.getCurrentUrl());

        webDriver.quit();
    }
}
