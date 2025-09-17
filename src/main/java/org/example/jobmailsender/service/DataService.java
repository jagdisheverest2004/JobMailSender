package org.example.jobmailsender.service;

import org.apache.poi.ss.usermodel.*;
import org.example.jobmailsender.model.Recipient;
import org.example.jobmailsender.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private RecipientRepository recipientRepository;

    public void processAndSaveXLSX(String filePath) throws IOException {
        List<Recipient> recipients = new ArrayList<>();

        try (FileInputStream excelFile = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(excelFile)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

            boolean isFirstRow = true;
            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Skip the header row
                }

                // Ensure the row is not empty and has at least 5 cells
                if (row.getPhysicalNumberOfCells() < 5) {
                    continue;
                }

                try {
                    Integer sno = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)).trim());
                    String name = dataFormatter.formatCellValue(row.getCell(1)).trim();
                    String email = dataFormatter.formatCellValue(row.getCell(2)).trim();
                    String title = dataFormatter.formatCellValue(row.getCell(3)).trim();
                    String company = dataFormatter.formatCellValue(row.getCell(4)).trim();

                    // Basic validation for email
                    if (email.contains("@")) {
                        recipients.add(new Recipient(sno,name, email, title, company));
                    }

                } catch (NumberFormatException | IllegalStateException e) {
                    System.err.println("Skipping row due to invalid data format: " + e.getMessage());
                }
            }
        }
        recipientRepository.saveAll(recipients);
        System.out.println("Successfully extracted and saved " + recipients.size() + " records to the database.");
    }
}