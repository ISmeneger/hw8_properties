package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebFormSteps {
    public static void openLongPage(WebDriver driver) throws InterruptedException {
        WebElement longPageButton = driver.findElement(By.xpath("//a[@href = 'long-page.html']"));
        Thread.sleep(2000);
        longPageButton.click();
    }

    public static void openInfiniteScrollPage(WebDriver driver) throws InterruptedException {
        WebElement infiniteScrollButton = driver.findElement(By.xpath("//a[@href = 'infinite-scroll.html']"));
        Thread.sleep(2000);
        infiniteScrollButton.click();
    }

    public static void openShadowDomPage(WebDriver driver) throws InterruptedException {
        WebElement shadowDomButton = driver.findElement(By.xpath("//a[@href = 'shadow-dom.html']"));
        Thread.sleep(2000);
        shadowDomButton.click();
    }

    //    @Step("Open cookie page")
    public static void openCookiesPage(WebDriver driver) throws InterruptedException {
        WebElement cookiesButton = driver.findElement(By.xpath("//a[@href = 'cookies.html']"));
        Thread.sleep(2000);
        cookiesButton.click();
    }

    public static void openFramesPage(WebDriver driver) throws InterruptedException {
        WebElement framesButton = driver.findElement(By.xpath("//a[@href = 'frames.html']"));
        Thread.sleep(2000);
        framesButton.click();
    }

    public static void openIFramesPage(WebDriver driver) throws InterruptedException {
        WebElement iFramesButton = driver.findElement(By.xpath("//a[@href = 'iframes.html']"));
        Thread.sleep(3000);
        iFramesButton.click();
    }

    public static void openDialogBoxesPage(WebDriver driver) throws InterruptedException {
        WebElement dialogBoxesButton = driver.findElement(By.xpath("//a[@href = 'dialog-boxes.html']"));
        Thread.sleep(2000);
        dialogBoxesButton.click();
    }

    public static void openWebStoragePage(WebDriver driver) throws InterruptedException {
        WebElement webStorageButton = driver.findElement(By.xpath("//a[@href = 'web-storage.html']"));
        Thread.sleep(2000);
        webStorageButton.click();
    }

    public static void openLoadingImagesPage(WebDriver driver) throws InterruptedException {
        WebElement loadingImagesButton = driver.findElement(By.xpath("//a[@href = 'loading-images.html']"));
        Thread.sleep(2000);
        loadingImagesButton.click();
    }

    public static void openSlowCalculatorPage(WebDriver driver) throws InterruptedException {
        WebElement slowCalculatorButton = driver.findElement(By.xpath("//a[@href = 'slow-calculator.html']"));
        Thread.sleep(2000);
        slowCalculatorButton.click();
    }

    public static void openLoginFormPage(WebDriver driver) throws InterruptedException {
        WebElement loginFormButton = driver.findElement(By.xpath("//a[@href = 'login-form.html']"));
        Thread.sleep(2000);
        loginFormButton.click();
    }
}
