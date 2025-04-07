package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static steps.WebFormSteps.openLoadingImagesPage;

public class LoadingImagesTests {
    WebDriver driver;
    WebDriverWait wait;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());


    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
        openLoadingImagesPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Loading images (ImplicitWait), проверка текста после загрузки всех изображений")
    void loadingImagesImplicitWaitTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement afterLoadingText = driver.findElement(By.xpath("//p[text() = 'Done!']"));

        assertEquals("Done!", afterLoadingText.getText());
    }

    @Test
    @DisplayName("Loading images (ExplicitWait), проверка того, что все 4 изображения загрузились на странице")
    void loadingImagesExplicitWaitTest() {
        List<WebElement> images =  wait.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath("//div[@id = 'image-container']/img"), 4));

        assertThat(images).hasSize(4);
    }

    @Test
    @DisplayName("Loading images (FluentWait), проверка того, что все 4 изображения загрузились на странице")
    void loadingImagesFluentWaitTest() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        List<WebElement> images =  wait.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath("//div[@id = 'image-container']/img"), 4));

        assertEquals(4, images.size());
    }
}
