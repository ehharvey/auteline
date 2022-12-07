package gui;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;

import controller.ServerApplication;

public class selenium {
    
    // Starts the server and initalizes the selenium
    public static void main(String[] args){
        SpringApplication.run(ServerApplication.class, args);

        // TODO Missing chrome driver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();

        driver.get("localhost:8080");

        driver.close();
    }

}
