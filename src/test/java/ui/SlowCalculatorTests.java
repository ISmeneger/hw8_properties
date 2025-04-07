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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static steps.WebFormSteps.openSlowCalculatorPage;

public class SlowCalculatorTests {
    WebDriver driver;
    WebDriverWait wait;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    By zeroButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '0']");
    By oneButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '1']");
    By twoButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '2']");
    By threeButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '3']");
    By fourButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '4']");
    By fiveButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '5']");
    By sixButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '6']");
    By sevenButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '7']");
    By eightButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '8']");
    By nineButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '9']");

    By plusButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '+']");
    By minusButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '-']");
    By divideButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '÷']");
    By multiplyButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = 'x']");
    By equalButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '=']");
    By pointButtonLocator = By.xpath("//div[@class = 'keys']/span[text() = '.']");
    By resultField = By.xpath("//div[@class = 'screen']");

    @BeforeEach
    void start() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(config.getBaseUrl());
        openSlowCalculatorPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка сложения на Slow calculator")
    void checkSlowCalcSumTest() {
        String number = "22";

        calcFiveButton();
        calcPlusButton();
        calcOneButton();
        calcSevenButton();
        calcEqualButton();

        wait.until(ExpectedConditions.textToBe(resultField, number));

        assertThat(getResult()).isEqualTo(number);
    }

    @Test
    @DisplayName("Проверка вычитания на Slow calculator")
    void checkSlowCalcSubtractionTest() {
        String number = "86";

        calcOneButton();
        calcTwoButton();
        calcZeroButton();
        calcMinusButton();
        calcThreeButton();
        calcFourButton();
        calcEqualButton();

        wait.until(ExpectedConditions.textToBe(resultField, number));

        assertThat(getResult()).isEqualTo(number);
    }

    @Test
    @DisplayName("Проверка умножения на Slow calculator")
    void checkSlowCalcMultiplyTest() {
        String number = "48";

        calcSixButton();
        calcMultiplyButton();
        calcEightButton();
        calcEqualButton();

        wait.until(ExpectedConditions.textToBe(resultField, number));

        assertThat(getResult()).isEqualTo(number);
    }

    @Test
    @DisplayName("Проверка деления на Slow calculator")
    void checkSlowCalcDivisionTest() {
        String number = "9";

        calcEightButton();
        calcOneButton();
        calcDivideButton();
        calcNineButton();
        calcEqualButton();

        wait.until(ExpectedConditions.textToBe(resultField, number));

        assertThat(getResult()).isEqualTo(number);
    }

    @Test
    @DisplayName("Проверка значений с плавающей точкой на Slow calculator")
    void checkSlowCalcDoubleTest() {
        String number = "24.5";

        calcFiveButton();
        calcZeroButton();
        calcMinusButton();
        calcTwoButton();
        calcFiveButton();
        calcPointButton();
        calcFiveButton();
        calcEqualButton();

        wait.until(ExpectedConditions.textToBe(resultField, number));

        assertThat(getResult()).isEqualTo(number);
    }

    private void calcZeroButton() {
        driver.findElement(zeroButtonLocator).click();
    }

    private void calcOneButton() {
        driver.findElement(oneButtonLocator).click();
    }

    private void calcTwoButton() {
        driver.findElement(twoButtonLocator).click();
    }

    private void calcThreeButton() {
        driver.findElement(threeButtonLocator).click();
    }

    private void calcFourButton() {
        driver.findElement(fourButtonLocator).click();
    }

    private void calcFiveButton() {
        driver.findElement(fiveButtonLocator).click();
    }

    private void calcSixButton() {
        driver.findElement(sixButtonLocator).click();
    }

    private void calcSevenButton() {
        driver.findElement(sevenButtonLocator).click();
    }

    private void calcEightButton() {
        driver.findElement(eightButtonLocator).click();
    }

    private void calcNineButton() {
        driver.findElement(nineButtonLocator).click();
    }

    private void calcPlusButton() {
        driver.findElement(plusButtonLocator).click();
    }

    private void calcMinusButton() {
        driver.findElement(minusButtonLocator).click();
    }

    private void calcDivideButton() {
        driver.findElement(divideButtonLocator).click();
    }

    private void calcMultiplyButton() {
        driver.findElement(multiplyButtonLocator).click();
    }

    private void calcEqualButton() {
        driver.findElement(equalButtonLocator).click();
    }

    private void calcPointButton() {
        driver.findElement(pointButtonLocator).click();
    }

    private String getResult() {
        WebElement result = driver.findElement(By.xpath("//div[@class = 'screen']"));
        return result.getText();
    }
}
