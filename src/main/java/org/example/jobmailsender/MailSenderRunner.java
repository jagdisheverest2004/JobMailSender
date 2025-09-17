package org.example.jobmailsender;

import org.example.jobmailsender.model.Recipient;
import org.example.jobmailsender.repository.RecipientRepository;
import org.example.jobmailsender.service.DataService;
import org.example.jobmailsender.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailSenderRunner implements CommandLineRunner {

    @Autowired
    private DataService xlsxDataService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RecipientRepository recipientRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Starting data extraction from XLSX...");
//        String filePath = "src/main/resources/CompanyWiseHRContactsHRContacts.xlsx";
//        xlsxDataService.processAndSaveXLSX(filePath);
//        System.out.println("Data extraction and saving to database complete.");
//    }

    @Override
    public void run(String... args) throws Exception {
        List<Recipient> recipients = recipientRepository.findAll();
        emailService.sendEmailWithAttachment(recipients, "src/main/resources/resume.pdf");
    }
}