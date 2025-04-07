package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static steps.WebFormSteps.openCookiesPage;

public class CookiesTests {
    WebDriver driver;
    WebDriver.Options options;
    Set<Cookie> cookies;
    Cookie date;
    Cookie addNewCookie = new Cookie("New President", "Donald Trump");
    String textBefore = "username=John Doe\ndate=10/07/2018";
    String textBlock = "username=John Doe\ndate=10/07/2018\nNew President=Donald Trump";
    String textAfter = "username=John Doe\nNew President=Donald Trump";
    String dateCookie = "10/07/2018";
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());


    @BeforeEach
    void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
        openCookiesPage(driver);
        options = driver.manage();
        cookies = options.getCookies();
        date = options.getCookieNamed("date");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка дефолтных куки на странице")
    void cookieTest() {
        displayCookiesWebElement();

        assertEquals(textBefore, getCookiesList());

        assertThat(cookies).hasSize(2);

        assert date != null;
        assertNotNull(date.getValue(), "date.getValue() should be not null");
        assertEquals(dateCookie, date.getValue());
        assertThat(date.getPath()).isEqualTo("/")
                .as(String.format("date.getPath() should be %s, but was %s", "/", date.getPath()));
    }

    @Test
    @DisplayName("Добавление новых cookie")
    void setAddNewCookieTest() {
        options.addCookie(addNewCookie);
        displayCookiesWebElement();

        assertEquals(textBlock, getCookiesList());

        String readValue = options.getCookieNamed(addNewCookie.getName()).getValue();
        System.out.println(readValue);
        assertThat(addNewCookie.getValue()).isEqualTo(readValue);

        cookies = options.getCookies();
        assertThat(cookies).hasSize(3);
    }

    @Test
    @DisplayName("Удаление существующих cookie")
    void deleteCookieTest() {
        options.addCookie(addNewCookie);
        cookies = options.getCookies();

        options.deleteCookie(date);
        assertThat(options.getCookies()).hasSize(cookies.size() - 1);

        displayCookiesWebElement();

        assertEquals(textAfter, getCookiesList());
    }

    private void displayCookiesWebElement() {
        driver.findElement(By.id("refresh-cookies")).click();
    }

    private String getCookiesList() {
        WebElement cookiesListElement = driver.findElement(By.id("cookies-list"));
        return cookiesListElement.getText();
    }
}

