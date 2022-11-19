package frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication{

    // Must run this main to turn on the web server GUI
    public static void main(String[] args){
        SpringApplication.run(ServerApplication.class, args);
    }
}