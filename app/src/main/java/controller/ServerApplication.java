package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;


@SpringBootApplication
public class ServerApplication extends WebMvcAutoConfiguration{

    // Must run this main to turn on the web server GUI
    public static void main(String[] args){
        SpringApplication.run(ServerApplication.class, args);
    }

}