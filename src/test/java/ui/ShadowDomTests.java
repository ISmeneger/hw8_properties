package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static steps.WebFormSteps.openShadowDomPage;

public class ShadowDomTests {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
        openShadowDomPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Tag("positive")
    void ShadowDomTest() {
        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textShadowDom = shadowRoot.findElement(By.cssSelector("p"));

        assertEquals("Hello Shadow DOM", textShadowDom.getText());
    }

    @Test
    @Tag("negative")
    void ShadowDomInvalidTest() {
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.cssSelector("p")));
    }
}
