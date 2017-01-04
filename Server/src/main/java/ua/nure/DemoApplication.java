package ua.nure;

import com.mysql.jdbc.Driver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(DemoApplication.class, args);
    }
}
