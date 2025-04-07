package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static steps.WebFormSteps.openWebStoragePage;

public class WebStorageTests {
    WebDriver driver;
    WebStorage webStorage;
    LocalStorage localStorage;
    SessionStorage sessionStorage;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
        openWebStoragePage(driver);
        webStorage = (WebStorage) driver;
        localStorage = webStorage.getLocalStorage();
        sessionStorage = webStorage.getSessionStorage();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка Web storage, добавление в Display local storage и проверка отображения")
    void displayLocalStorageTest() {
        driver.findElement(By.id("display-local")).click();
        System.out.printf("Local storage elements: {%s}\n", localStorage.size());

        localStorage.setItem("Name", "Last name");
        assertThat(sessionStorage.size()).isEqualTo(2);

        driver.findElement(By.id("display-local")).click();
    }

    @Test
    @DisplayName("Проверка Web storage, добавление в Display session storage и проверка отображения")
    void displaySessionStorageTest() {
        driver.findElement(By.id("display-session")).click();

        sessionStorage.keySet()
                .forEach(key -> System.out.printf("Session storage: {%s}={%s}\n", key, sessionStorage.getItem(key)));
        assertThat(sessionStorage.size()).isEqualTo(2);

        sessionStorage.setItem("new element", "new value");
        assertThat(sessionStorage.size()).isEqualTo(3);

        driver.findElement(By.id("display-session")).click();
    }
}

