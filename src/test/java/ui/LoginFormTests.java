package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static steps.WebFormSteps.openLoginFormPage;

public class LoginFormTests {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    private static final String LOGIN_FORM_TEXT = "Login form";
    private static final String SUCCESSFUL_LOGIN_TEXT = "Login successful";
    private static final String INVALID_LOGIN_TEXT = "Invalid credentials";

    @BeforeEach
    void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(config.getBaseUrl());
        openLoginFormPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка Login form")
    void openLoginPageTest() {
        WebElement title = driver.findElement(By.className("display-6"));

        assertEquals(config.getBaseUrl() + "login-form.html", driver.getCurrentUrl());
        assertEquals(LOGIN_FORM_TEXT, title.getText());
    }

    @Test
    @DisplayName("Проверка успешной авторизации с валидными данными")
    void signInTest() {

        usernameData(config.getUsername());
        passwordData(config.getPassword());
        submitButtonClick();

        assertEquals(SUCCESSFUL_LOGIN_TEXT, getMessageText());
    }

    @Test
    @DisplayName("Проверка ввода не корректных имени и пароля")
    void invalidCredentialsTest() {

        usernameData(config.getInvalidUsername());
        passwordData(config.getInvalidPassword());
        submitButtonClick();

        assertEquals(INVALID_LOGIN_TEXT, getMessageText());
    }

    private void usernameData(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
    }

    private void passwordData(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    private String getMessageText() {
        WebElement massage = driver.findElement(By.className("alert"));
        return massage.getText();
    }

    private void submitButtonClick() {
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
    }
}
