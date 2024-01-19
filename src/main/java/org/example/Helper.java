package org.example;
import org.example.models.RealEstate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Helper {
    public static WebDriver driver;
    public static WebDriverWait wait;


    public static void setUp() {
        if (driver != null) {
            return;
        }
        driver = new ChromeDriver();
        RealEstate.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        RealEstate.wait = wait;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        acceptCookies();

    }

    public static void acceptCookies() {
        driver.get("https://www.aruodas.lt/");
        driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();

    }
    public static void wait(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
        }
    }

}