package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static constants.Constants.LOREM_IPSUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static steps.WebFormSteps.openFramesPage;

public class FramesTests {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
        openFramesPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Tag("positive")
    void FramesTest1() {
        driver.switchTo().frame(frameBodyElement());

        assertThat(driver.findElement(By.className("lead")).getText())
                .contains(LOREM_IPSUM);
    }

    @Test
    @Tag("positive")
    void FramesTest2() {
        driver.switchTo().frame(frameBodyElement());
        driver.switchTo().defaultContent();

        WebElement frameHeaderElement = driver.findElement(By.name("frame-header"));
        driver.switchTo().frame(frameHeaderElement);

        assertThat(driver.findElement(By.className("display-6")).getText()).contains("Frames");
    }

    @Test
    @Tag("negative")
    void FramesInvalidTest() {
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("lead")));

        driver.switchTo().frame(frameBodyElement());

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("display-6")));
    }

    private WebElement frameBodyElement() {
        return driver.findElement(By.name("frame-body"));
    }
}

