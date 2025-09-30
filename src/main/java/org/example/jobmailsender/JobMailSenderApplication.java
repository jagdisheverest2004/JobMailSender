package org.example.jobmailsender;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobMailSenderApplication {

    public static void main(String[] args) {
        Dotenv.load();
        SpringApplication.run(JobMailSenderApplication.class, args);
    }
}
