package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static steps.WebFormSteps.openLongPage;

public class LongPageTests {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
        openLongPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void longPageTest() {
        WebElement footerLink = driver.findElement(By.className("text-muted"));
        new Actions(driver)
                .scrollToElement(footerLink)
                .perform();

        assertEquals("Copyright © 2021-2025 Boni García", footerLink.getText());
    }

    @Test
    void longPageTest2() {
        WebElement footerLink = driver.findElement(By.className("text-muted"));
        int maxPageDownSteps = 3;
        for (int i = 0; i < maxPageDownSteps; i++) {
            new Actions(driver)
                    .keyDown(Keys.PAGE_DOWN)
                    .perform();
        }

        assertThat(footerLink.getText()).contains("Copyright © 2021-2025");
    }

    @Test
    void longPageTest3() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement footerLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("text-muted")));
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, footerLink);

        assertThat(footerLink.getText()).contains("Copyright © 2021-2025");
    }
}

