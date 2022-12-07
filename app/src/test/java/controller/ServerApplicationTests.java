package controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ServerApplicationTests {
    private WebDriver driver;
    ConfigurableApplicationContext webapp;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);

        webapp = SpringApplication.run(ServerApplication.class);
    }

    
    @Test
    public void T001_test() {
        driver.navigate().to("https://google.ca");
        driver.findElement(By.className("lnXdpd")); // tag of their logo
    }

    @Test
    public void T002_testHomeBasic() {
        driver.navigate().to("http://localhost:8080");

        driver.findElement(By.className("logo")); // Maybe we could add a unique class identifier
                                                            // to differentiate the home logo from other logos
    }


    @Test
    public void T003_testGetToLoginPage() {
        driver.navigate().to("http://localhost:8080");

        driver.findElement(By.xpath("/html/body/div/div[2]/div/input")).click(); // Login button

        driver.findElement(By.xpath("/html/body/div/div[2]/div/h4")); // Login text
    }


    @After
    public void tearDown() {
        driver.close();
        driver.quit();
        webapp.close();
    }
}