package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static steps.WebFormSteps.openDialogBoxesPage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DialogBoxesTests {
    WebDriver driver;
    WebDriverWait wait;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    private static final String ALERT_TEXT = "Hello world!";
    private static final String CONFIRM_TEXT = "Is this correct?";
    private static final String CONFIRM_ACCEPT_TEXT = "You chose: true";
    private static final String CONFIRM_CANCEL_TEXT = "You chose: false";
    private static final String PROMPT_TEXT = "Please enter your name";
    private static final String PROMPT_ACCEPT_TEXT = "You typed: Test";
    private static final String PROMPT_CANCEL_TEXT = "You typed: null";
    private static final String MODAL_ACCEPT_TEXT = "You chose: Save changes";
    private static final String MODAL_CANCEL_TEXT = "You chose: Close";

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
        openDialogBoxesPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка Launch alert")
    @Order(1)
    void launchAlertTest() {
        driver.findElement(By.id("my-alert")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert launchAlert = driver.switchTo().alert();
        String launchAlertText = launchAlert.getText();

        assertThat(launchAlertText).isEqualTo(ALERT_TEXT);
        launchAlert.accept();
    }

    @Test
    @DisplayName("Проверка Launch confirm и нажатие на кнопку 'ОК'")
    @Order(2)
    void launchConfirmChooseOkTest() {
        clickToLaunchConfirm();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert launchConfirm = driver.switchTo().alert();
        String launchConfirmText = launchConfirm.getText();

        assertThat(launchConfirmText).isEqualTo(CONFIRM_TEXT);
        launchConfirm.accept();

        assertThat(driver.findElement(By.id("confirm-text")).getText()).isEqualTo(CONFIRM_ACCEPT_TEXT);
    }

    @Test
    @DisplayName("Проверка Launch confirm и нажатие на кнопку 'Отмена'")
    @Order(3)
    void launchConfirmChooseCancelTest() {
        clickToLaunchConfirm();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert launchConfirm = driver.switchTo().alert();
        launchConfirm.dismiss();

        assertThat(driver.findElement(By.id("confirm-text")).getText()).isEqualTo(CONFIRM_CANCEL_TEXT);
    }

    @Test
    @DisplayName("Проверка Launch prompt и нажатие на кнопку 'ОК'")
    @Order(4)
    void launchPromptChooseOkTest() {
        clickToLaunchPrompt();
        Alert launchPrompt = driver.switchTo().alert();
        String launchPromptText = launchPrompt.getText();

        assertThat(launchPromptText).isEqualTo(PROMPT_TEXT);
        launchPrompt.sendKeys("Test");
        launchPrompt.accept();

        assertThat(driver.findElement(By.id("prompt-text")).getText()).isEqualTo(PROMPT_ACCEPT_TEXT);
    }


    @Test
    @DisplayName("Проверка Launch prompt и нажатие на кнопку 'Отмена'")
    @Order(5)
    void launchPromptChooseCancelTest() {
        clickToLaunchPrompt();
        Alert launchPrompt = driver.switchTo().alert();
        launchPrompt.sendKeys("Test");
        launchPrompt.dismiss();

        assertThat(driver.findElement(By.id("prompt-text")).getText()).isEqualTo(PROMPT_CANCEL_TEXT);
    }

    @Test
    @DisplayName("Проверка Launch modal и нажатие на кнопку 'Save changes'")
    @Order(6)
    void launchModalChooseSaveChangeTest() {
        clickToLaunchModal();

        WebElement saveChanges = driver.findElement(By.xpath("//button[normalize-space() = 'Save changes']"));
        wait.until(ExpectedConditions.elementToBeClickable(saveChanges));
        saveChanges.click();

        assertThat(driver.findElement(By.id("modal-text")).getText()).isEqualTo(MODAL_ACCEPT_TEXT);
    }

    @Test
    @DisplayName("Проверка Launch modal и нажатие на кнопку 'Close'")
    @Order(7)
    void launchModalChooseCloseTest() {
        clickToLaunchModal();

        WebElement close = driver.findElement(By.xpath("//button[text() = 'Close']"));
        wait.until(ExpectedConditions.elementToBeClickable(close));
        close.click();

        assertThat(driver.findElement(By.id("modal-text")).getText()).isEqualTo(MODAL_CANCEL_TEXT);
    }

    private void clickToLaunchConfirm() {
        driver.findElement(By.id("my-confirm")).click();
    }

    private void clickToLaunchPrompt() {
        driver.findElement(By.id("my-prompt")).click();
    }

    private void clickToLaunchModal() {
        driver.findElement(By.id("my-modal")).click();
    }
}

